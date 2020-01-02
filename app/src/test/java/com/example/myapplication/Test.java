package com.example.myapplication;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Test {

    @org.junit.Test
    public void TestClone() throws CloneNotSupportedException {
        User user = new User("xq", 18);
        user.setName("第一次修改");
        System.out.println("=====================" + user.toString());
        User userCopy = (User) user.clone();
        System.out.println("userCopy=====================" + userCopy.toString());

        user.setName("第二次修改");
        System.out.println("=====================" + user.toString());
        System.out.println("userCopy=====================" + userCopy.toString());
    }
    //=====================User{name='第一次修改', age=18}
    //userCopy=====================User{name='第一次修改', age=18}
    //=====================User{name='第二次修改', age=18}
    //userCopy=====================User{name='第一次修改', age=18}

    public class User implements Cloneable {
        String name;
        Integer age;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @NonNull
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof User)) return false;
            User user = (User) o;
            return getName().equals(user.getName()) &&
                    getAge().equals(user.getAge());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getName(), getAge());
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
