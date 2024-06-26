package com.example.lectureservice.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockManager {
    private static final ConcurrentHashMap<String, Lock> locks = new ConcurrentHashMap<>();

    public static void lock(String key) {
        Lock lock = locks.computeIfAbsent(key, k -> new ReentrantLock());
        lock.lock();
    }

    public static void unlock(String key) {
        Lock lock = locks.get(key);
        if (lock != null) {
            lock.unlock();
        }
    }
}
