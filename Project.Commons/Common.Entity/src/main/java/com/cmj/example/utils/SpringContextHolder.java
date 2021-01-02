package com.cmj.example.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/1/2
 */
@Component
public enum SpringContextHolder {

    INSTANCE();

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 获取 SpringContextHolder 实例
     *
     * @param
     * @return com.cmj.example.utils.SpringContextHolder
     * @author mengjie_chen
     * @date 2021/1/2
     */
    public static SpringContextHolder getInstance() {
        return SpringContextHolder.INSTANCE;
    }

    /**
     * 获取容器中的组件
     *
     * @param clazz
     * @return T
     * @author mengjie_chen
     * @date 2021/1/2
     */
    public static <T> T getBean(Class<T> clazz) {
        return getInstance().applicationContext.getBean(clazz);
    }
}
