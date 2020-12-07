package com.cmj.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.cmj.example.dao.IUserDao;
import com.cmj.example.mapper.UserMapper;
import com.cmj.example.vo.AmazonProperties;
import com.cmj.example.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Autowired
    private UserMapper userMapper;

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

    @PostMapping("/save")
    @ResponseBody
    public String save() {
        UserVo userVo = UserVo.UserVoBuilder.userVo().userName("李四").password("123456").age(23).outerId(amazonProperties.getAssociateId()).build();
        userDao.save(userVo);
        UserVo one = userDao.getOne(userVo.getUserId());
        return JSONObject.toJSONString(one);
    }

    @GetMapping("/getAllUser")
    @ResponseBody
    public String getAllUser() {
        List<UserVo> userVoList = userMapper.getAllUser();
        return JSONObject.toJSONString(userVoList);
    }
}
