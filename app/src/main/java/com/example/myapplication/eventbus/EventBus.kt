package com.example.myapplication.eventbus

import android.util.Log
import kotlinx.coroutines.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.coroutines.CoroutineContext

/**
 *
 *  https://github.com/fengzhizi715/EventBus
 *
 *  Channel 可以实现协程之间的数据通信。
 *  Kotlin 的 Channel 与 Java 的 BlockingQueue 类似。
 *  BlockingQueue 的 put 和 take 操作，相当于 Channel 的 send 和 receive 操作，但是 BlockingQueue 是阻塞操作而 Channel 都是挂起操作。
 *
 *  EventBus 用于注册普通事件、Sticky 事件，事件的发布等等。
 */
val UI: CoroutineDispatcher = Dispatchers.Main

object EventBus : CoroutineScope {

    private val TAG = "EventBus"

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext = Dispatchers.Default + job

    private val contextMap = ConcurrentHashMap<String, MutableMap<Class<*>, EventData<*>>>()
    private val mStickyEventMap = ConcurrentHashMap<Class<*>, Any>()

    @JvmStatic
    fun <T> register(
        contextName: String,
        eventDispatcher: CoroutineDispatcher = UI,
        eventClass: Class<T>,
        eventCallback: (T) -> Unit
    ) {
        val eventDataMap = if (contextMap.containsKey(contextName)) {
            contextMap[contextName]!!
        } else {
            val eventDataMap = mutableMapOf<Class<*>, EventData<*>>()
            contextMap[contextName] = eventDataMap
            eventDataMap
        }

        eventDataMap[eventClass] = EventData(this, eventDispatcher, eventCallback)
    }

    @JvmStatic
    fun <T> register(
        contextName: String,
        eventDispatcher: CoroutineDispatcher = UI,
        eventClass: Class<T>,
        eventCallback: (T) -> Unit,
        eventFail: (Throwable) -> Unit
    ) {
        val eventDataMap = if (contextMap.containsKey(contextName)) {
            contextMap[contextName]!!
        } else {
            val eventDataMap = mutableMapOf<Class<*>, EventData<*>>()
            contextMap[contextName] = eventDataMap
            eventDataMap
        }

        eventDataMap[eventClass] = EventData(this, eventDispatcher, eventCallback, eventFail)
    }

    @JvmStatic
    fun <T> registerSticky(
        contextName: String,
        eventDispatcher: CoroutineDispatcher = UI,
        eventClass: Class<T>,
        eventCallback: (T) -> Unit
    ) {
        val eventDataMap = if (contextMap.containsKey(contextName)) {
            contextMap[contextName]!!
        } else {
            val eventDataMap = mutableMapOf<Class<*>, EventData<*>>()
            contextMap[contextName] = eventDataMap
            eventDataMap
        }

        eventDataMap[eventClass] = EventData(this, eventDispatcher, eventCallback)

        val event = mStickyEventMap[eventClass]
        event?.let {

            postEvent(it)
        }
    }

    @JvmStatic
    fun <T> registerSticky(
        contextName: String,
        eventDispatcher: CoroutineDispatcher = UI,
        eventClass: Class<T>,
        eventCallback: (T) -> Unit,
        eventFail: (Throwable) -> Unit
    ) {
        val eventDataMap = if (contextMap.containsKey(contextName)) {
            contextMap[contextName]!!
        } else {
            val eventDataMap = mutableMapOf<Class<*>, EventData<*>>()
            contextMap[contextName] = eventDataMap
            eventDataMap
        }

        eventDataMap[eventClass] = EventData(this, eventDispatcher, eventCallback, eventFail)

        val event = mStickyEventMap[eventClass]
        event?.let {

            postEvent(it)
        }
    }

    @JvmStatic
    fun post(event: Any, delayTime: Long = 0) {

        if (delayTime > 0) {
            launch {
                delay(delayTime)
                postEvent(event)
            }
        } else {
            postEvent(event)
        }
    }

    @JvmStatic
    fun postSticky(event: Any) {

        mStickyEventMap[event.javaClass] = event
    }

    @JvmStatic
    fun unregisterAllEvents() {

        Log.i(TAG, "unregisterAllEvents()")

        coroutineContext.cancelChildren()
        for ((_, eventDataMap) in contextMap) {
            eventDataMap.values.forEach {
                it.cancel()
            }
            eventDataMap.clear()
        }
        contextMap.clear()
    }

    @JvmStatic
    fun unregister(contextName: String) {

        Log.i(TAG, "$contextName")

        val cloneContexMap = ConcurrentHashMap<String, MutableMap<Class<*>, EventData<*>>>()
        cloneContexMap.putAll(contextMap)
        val map = cloneContexMap.filter { it.key == contextName }
        for ((_, eventDataMap) in map) {
            eventDataMap.values.forEach {
                it.cancel()
            }
            eventDataMap.clear()
        }
        contextMap.remove(contextName)
    }

    @JvmStatic
    fun <T> removeStickyEvent(eventType: Class<T>) {
        mStickyEventMap.remove(eventType)
    }

    private fun postEvent(event: Any) {

        val cloneContexMap = ConcurrentHashMap<String, MutableMap<Class<*>, EventData<*>>>()
        cloneContexMap.putAll(contextMap)
        for ((_, eventDataMap) in cloneContexMap) {
            eventDataMap.keys
                .firstOrNull { it == event.javaClass || it == event.javaClass.superclass }
                ?.let { key -> eventDataMap[key]?.postEvent(event) }
        }
    }
}