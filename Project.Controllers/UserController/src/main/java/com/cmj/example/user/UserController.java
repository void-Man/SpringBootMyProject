package com.cmj.example.user;

import com.alibaba.fastjson.JSONObject;
import com.cmj.example.UserService;
import com.cmj.example.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/3/5
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getFirst100User")
    @ResponseBody
    public String getFirst100User() {
        List<UserVo> userVoList = userService.getFirst100User();
        return JSONObject.toJSONString(userVoList);
    }

}
