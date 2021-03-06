package com.cmj.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.cmj.example.utils.StringUtils;
import com.cmj.example.vo.UserVo;
import com.cmj.example.base.UserBase;
import com.cmj.example.base.UserBaseExample;
import com.cmj.example.jpa.UserRepository;
import com.cmj.example.mapper.UserBaseMapper;
import com.cmj.example.mapper.UserMapper;
import com.cmj.example.vo.AmazonProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author mengjie_chen
 * @description date 2020/12/3
 */
@Controller
@RequestMapping("/tester")
public class TesterController {
    private static final Logger logger = LoggerFactory.getLogger(TesterController.class);

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
        UserBase userBase = UserBase.builder()
                .name(getRandomJianHan(3))
                .age(11).password("123456")
                .createTime(new Date())
                .creator(0)
                .build();
        userBaseMapper.insertSelective(userBase);
        List<UserBase> userBaseList = userBaseMapper.selectByExample(new UserBaseExample().createCriteria().example());
        return JSONObject.toJSONString(userBaseList);
    }

    @PostMapping("/update")
    @ResponseBody
    public String update(Integer userId) {
        UserBase userBase = UserBase.builder()
                .id(userId)
                .age(11)
                .password(StringUtils.randomUUID())
                .updateTime(new Date())
                .updater(userId)
                .build();
        userBaseMapper.updateByPrimaryKeySelective(userBase);
        UserBase resBase = userBaseMapper.selectByPrimaryKey(userId);
        return JSONObject.toJSONString(resBase);
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
        List<UserBase> userBaseList = userBaseMapper.selectByExampleSelective(new UserBaseExample().createCriteria()
                .andIdBetween(600, 650)
                .example(), UserBase.Column.id, UserBase.Column.name, UserBase.Column.age, UserBase.Column.password, UserBase.Column.outerId);
        logger.debug("com.cmj.example.controller.TesterController.getAllUserByMapper---->111");
        logger.info("com.cmj.example.controller.TesterController.getAllUserByMapper---->222");
        logger.error("com.cmj.example.controller.TesterController.getAllUserByMapper---->333");
        return JSONObject.toJSONString(userBaseList);
    }

    public static String getRandomJianHan(int len) {
        String ret = "";
        for (int i = 0; i < len; i++) {
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); // 获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); // 获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                str = new String(b, "GBK"); // 转成中文
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            ret += str;
        }
        return ret;
    }

    @GetMapping("/user")
    @ResponseBody
    public String getUser() {
        return JSONObject.toJSONString("getUser");
    }

    @PostMapping("/user")
    @ResponseBody
    public String addUser() {
        return JSONObject.toJSONString("addUser");
    }

    @PutMapping("/user")
    @ResponseBody
    public String putUser() {
        return JSONObject.toJSONString("putUser");
    }

    @DeleteMapping("/user")
    @ResponseBody
    public String deleteUser() {
        return JSONObject.toJSONString("deleteUser");
    }
}
