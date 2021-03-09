package com.cmj.example.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/3/9
 */
@Component
public class SpringTesterBeanPostProcessor implements BeanPostProcessor {
    private static final Logger logger = LoggerFactory.getLogger(SpringTesterBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Person){
            logger.info("SpringTesterBeanPostProcessor.postProcessBeforeInitialization() -----> 进入SpringTesterBeanPostProcessor.postProcessBeforeInitialization)方法");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Person){
            logger.info("SpringTesterBeanPostProcessor.postProcessAfterInitialization() -----> 进入SpringTesterBeanPostProcessor.postProcessAfterInitialization)方法");
        }
        return bean;
    }
}
