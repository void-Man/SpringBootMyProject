package com.cmj.example.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/3/9
 */
@Component
public class HumanVo {
    private static final Logger logger = LoggerFactory.getLogger(HumanVo.class);

    private String name = "HumanVo";
    @Autowired
    private Person person;

    public HumanVo() {
        logger.info("HumanVo.HumanVo -----> 进入HumanVo构造方法");
    }

//    public HumanVo(Person person) {
//        logger.info("HumanVo.HumanVo -----> 进入HumanVo构造方法");
//    }
}
