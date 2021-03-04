package com.cmj.example;

import com.alibaba.fastjson.JSONObject;
import com.cmj.example.vo.UserTestVo;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/29
 */
public class ThreadLocalTester {

    private static final ThreadLocal<UserTestVo> local = new ThreadLocal<>();

    public static void main(String[] args) {
        initUser();
//        editUser();
        System.out.println(JSONObject.toJSONString(getUser()));
    }

    public static void initUser() {
        UserTestVo userTestVo = UserTestVo.UserVoBuilder.userVo()
                .userId(111l)
                .userName("张三")
                .age(10)
                .build();
        local.set(userTestVo);
    }

    public static void editUser() {
        UserTestVo userTestVo = local.get();
        userTestVo.setUserName("李四");
        userTestVo.setAge(20);
    }

    public static UserTestVo getUser() {
        return local.get();
    }

}
