package com.cmj.example.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/15
 */
@Component
@PropertySource(value = {"classpath:userInfo.properties"})
@ConfigurationProperties(prefix = "pro.user")
public class UserConfigurationPropertiesVo {

    private Long id;
    private String name;
    private Integer age;
    private Integer sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
