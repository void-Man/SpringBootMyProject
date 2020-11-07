package com.cmj.example.utils;

public interface DistributedLock {

    /**
     * 加锁
     * @param lockName
     * @return
     */
    boolean lock(String lockName);

    /**
     * 解锁
     * @param lockName
     */
    boolean release(String lockName);

}
