package com.cmj.example;

import org.junit.Test;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/15
 */
public class BiliBiliTest {

    @Test
    public void test() {
        StringBuilder stringBuilder = new StringBuilder();
        String str = "https://www.bilibili.com/video/BV1PJ411n7xZ";
        int end = 381;
        int start = 1;
        int CONSTANT = 70;
        String path = "F:\\视频\\尚硅谷JVM全套教程，百万播放，全网巅峰";
        for (int i = 1; i <= (end % CONSTANT == 0 ? (end - start + 1) / CONSTANT : (end - start) / CONSTANT + 1); i++) {
            stringBuilder.append("F:&&cd ");
            stringBuilder.append(path);
            stringBuilder.append("&&you-get --format=dash-flv ");
            for (int j = start; j < CONSTANT + start; j++) {
                if ((i - 1) * CONSTANT + j > end) {
                    break;
                }
                stringBuilder.append(str);
                stringBuilder.append("?p=");
                stringBuilder.append((i - 1) * CONSTANT + j);
                if (j == CONSTANT + start - 1) {
                    stringBuilder.append("\n");
                } else {
                    stringBuilder.append(" ");
                }
            }
        }
        System.out.println(stringBuilder.toString());
    }

}
