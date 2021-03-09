package com.cmj.example.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/3/9
 */
@Component
public class Person implements BeanFactoryAware, BeanNameAware, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(Person.class);

    private String name;
    private String address;
    private String phone;

    public Person() {
        logger.info("Person.Person -----> 进入Person构造方法");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        logger.info("Person.Person.setName() -----> 进入Person.setName()方法");
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        logger.info("Person.Person.setAddress() -----> 进入Person.setAddress()方法");
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        logger.info("Person.Person.setPhone() -----> 进入Person.setPhone()方法");
        this.phone = phone;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        logger.info("Person.Person.setBeanFactory() -----> 进入Person.setBeanFactory()方法");
    }

    @Override
    public void setBeanName(String name) {
        logger.info("Person.Person.setBeanName() -----> 进入Person.setBeanName()方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Person.Person.afterPropertiesSet() -----> 进入Person.afterPropertiesSet()方法");
    }

    @PostConstruct
    public void init() {
        logger.info("Person.Person.init() -----> 进入Person.init()方法");
    }
}
