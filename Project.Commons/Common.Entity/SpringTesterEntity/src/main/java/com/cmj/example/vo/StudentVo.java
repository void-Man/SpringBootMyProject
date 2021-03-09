package com.cmj.example.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/3/9
 */
@Component
@Order(1)
public class StudentVo {
    private static final Logger logger = LoggerFactory.getLogger(StudentVo.class);

    private String name;
//    @Autowired
//    private HumanVo humanVo;

    public StudentVo() {
        logger.info("StudentVo.StudentVo -----> 进入StudentVo构造方法");
    }
//
//    public StudentVo(Person person) {
//        this.person = person;
//    }

//    public Person getPerson() {
//        return person;
//    }
//
//    public void setPerson(Person person) {
//        this.person = person;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
