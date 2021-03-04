package com.cmj.example;

import com.alibaba.fastjson.JSONObject;
import com.cmj.example.vo.UserVo;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/29
 */
public class ThreadLocalTester {

    private static final ThreadLocal<UserVo> local = new ThreadLocal<>();

    public static void main(String[] args) {
        initUser();
//        editUser();
        System.out.println(JSONObject.toJSONString(getUser()));
    }

    public static void initUser() {
        UserVo userVo = UserVo.UserVoBuilder.userVo()
                .userId(111l)
                .userName("张三")
                .age(10)
                .build();
        local.set(userVo);
    }

    public static void editUser() {
        UserVo userVo = local.get();
        userVo.setUserName("李四");
        userVo.setAge(20);
    }

    public static UserVo getUser() {
        return local.get();
    }

}
