package com.cmj.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mengjie_chen
 * @description Redis分布式锁
 * @date 2020/11/7
 */
public enum  RedisDistributedLock implements DistributedLock{
    INSTANCE()
    ;

    private static final Logger log = LoggerFactory.getLogger(RedisDistributedLock.class);
    /**
     * 过期时间
     */
    private static final int EXPIRE_SECONDS = 60;

    @Override
    public boolean lock(String lockName) {

        return false;
    }

    @Override
    public boolean release(String lockName) {
        return false;
    }
}
