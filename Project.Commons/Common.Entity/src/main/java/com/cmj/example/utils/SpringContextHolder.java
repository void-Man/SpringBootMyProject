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
public class SpringContextHolder {

    private SpringContextHolder() {
    }

    private static ApplicationContext applicationContext;

    /**
     * setter 属性注入
     *
     * @param applicationContext
     * @return void
     * @author mengjie_chen
     * @date 2021/1/3
     */
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
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
        return applicationContext.getBean(clazz);
    }
}
