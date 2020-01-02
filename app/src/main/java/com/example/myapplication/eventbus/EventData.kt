package com.example.myapplication.eventbus

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 *  EventData 通过 channel 实现真正的发送、消费事件。
 */
data class EventData<T>(
    val coroutineScope: CoroutineScope,
    val eventDispatcher: CoroutineDispatcher,
    val onEvent: (T) -> Unit,
    val exception: ((Throwable)->Unit)? = null
) {

    private val channel = Channel<T>()

    init {
        coroutineScope.launch {
            channel.consumeEach { // 消费者循环地消费消息
                launch(eventDispatcher) {

                    if (exception!=null) {

                        try{
                            onEvent(it)
                        } catch (e:Exception) {

                            exception.invoke(e)
                        }
                    } else {

                        onEvent(it)
                    }
                }
            }
        }
    }

    fun postEvent(event: Any) {
        if (!channel.isClosedForSend) {

            coroutineScope.launch {
                channel.send(event as T)
            }
        } else {
            println("Channel is closed for send")
        }
    }

    fun cancel() {
        channel.cancel()
    }
}