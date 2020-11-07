package com.cmj.example.utils.common;

import java.util.Collection;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/10/11
 */
public class CommonUtils {

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

    public static boolean isNullOrEmpty(Collection<? extends Object> userSessionList) {
        return Objects.isNull(userSessionList) || userSessionList.size() == 0;
    }

    public static Long randomLongId() {
        long bits, val;
        do {
            bits = (new Random().nextLong() << 1) >>> 1;
            val = bits % 9999999999999999L;
        } while (bits - val + (9999999999999999L - 1) < 0L);

        return val;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            System.out.println(CommonUtils.randomLongId());
        }
    }
}
