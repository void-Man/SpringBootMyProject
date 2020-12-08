package com.cmj.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.cmj.example.UserVo;
import com.cmj.example.base.UserBase;
import com.cmj.example.base.UserBaseExample;
import com.cmj.example.jpa.UserRepository;
import com.cmj.example.mapper.UserBaseMapper;
import com.cmj.example.mapper.UserMapper;
import com.cmj.example.vo.AmazonProperties;
import com.cmj.example.vo.UserRepositoryVo;
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
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserBaseMapper userBaseMapper;

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
        UserRepositoryVo userRepositoryVo = UserRepositoryVo.UserRepositoryVoBuilder.userRepositoryVo()
                .userName("李四")
                .password("123456")
                .age(23)
                .outerId(amazonProperties.getAssociateId())
                .build();
        userRepository.save(userRepositoryVo);
        UserRepositoryVo one = userRepository.getOne(userRepositoryVo.getUserId());
        return JSONObject.toJSONString(one);
    }

    @GetMapping("/getAllUser")
    @ResponseBody
    public String getAllUser() {
        List<UserVo> userVoList = userMapper.getAllUser();
        return JSONObject.toJSONString(userVoList);
    }

    @GetMapping("/getAllUserByMapper")
    @ResponseBody
    public String getAllUserByMapper() {
        List<UserBase> userBaseList = userBaseMapper.selectByExample(new UserBaseExample().createCriteria().example());
        return JSONObject.toJSONString(userBaseList);
    }
}
