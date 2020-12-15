package com.cmj.example.utils;

import java.util.Objects;
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

    /**
     * 是否是空
     *
     * @param
     * @return java.lang.String
     * @author mengjie_chen
     * @date 2020/10/11
     */
    public static boolean isEmpty(String str) {
        return Objects.isNull(str) || "".equals(str);
    }

    /**
     * 是否不是空
     *
     * @param
     * @return java.lang.String
     * @author mengjie_chen
     * @date 2020/10/11
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
