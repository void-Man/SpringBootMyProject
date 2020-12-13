package com.cmj.example;

import java.util.UUID;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/13
 */
public class StringUtils {
    /**
     * 随机生成UUID
     *
     * @param
     * @return java.lang.String
     * @author mengjie_chen
     * @date 2020/10/11
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
