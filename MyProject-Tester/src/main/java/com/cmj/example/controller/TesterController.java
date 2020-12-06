package com.cmj.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.cmj.example.dao.IUserDao;
import com.cmj.example.vo.AmazonProperties;
import com.cmj.example.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author mengjie_chen
 * @description date 2020/12/3
 */
@Controller
@RequestMapping("/tester")
public class TesterController {
    private final AmazonProperties amazonProperties;
    @Autowired
    private IUserDao userDao;

    public TesterController(AmazonProperties amazonProperties) {
        this.amazonProperties = amazonProperties;
    }

    @GetMapping("/test01")
    @ResponseBody
    public String test() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 111111);
        jsonObject.put("name", "张三");
        jsonObject.put("associateId", amazonProperties.getAssociateId());
        return JSONObject.toJSONString(jsonObject);
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    @ResponseBody
    public String save() {
        UserVo userVo = UserVo.UserVoBuilder.userVo().userName("李四").password("123456").age(23).outerId(amazonProperties.getAssociateId()).build();
        userDao.save(userVo);
        UserVo one = userDao.getOne(1L);
        return JSONObject.toJSONString(one);
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    public String getUser() {
        UserVo userVo = UserVo.UserVoBuilder.userVo().userName("李四").password("123456").age(23).outerId(amazonProperties.getAssociateId()).build();
        return JSONObject.toJSONString(userVo);
    }
}
