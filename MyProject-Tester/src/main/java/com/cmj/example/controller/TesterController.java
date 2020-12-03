package com.cmj.example.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author mengjie_chen
 * @description date 2020/12/3
 */
@Controller
@RequestMapping("/tester")
public class TesterController {

    @GetMapping("/test01")
    @ResponseBody
    public String test() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 111111);
        jsonObject.put("name", "张三");
        return JSONObject.toJSONString(jsonObject);
    }

}
