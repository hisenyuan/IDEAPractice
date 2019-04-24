package com.hisen.test;

import com.hisen.interview.MultithreadingSingleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TestTrackSingleton {
    public static void main(String[] args) {
        Class<MultithreadingSingleton> classType = MultithreadingSingleton.class;
        Constructor<MultithreadingSingleton> constructor = null;
        try {
            constructor = classType.getDeclaredConstructor(null);
            constructor.setAccessible(true);
            MultithreadingSingleton singleton = constructor.newInstance();
            MultithreadingSingleton instance = MultithreadingSingleton.getInstance();
            System.out.println(singleton == instance);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
