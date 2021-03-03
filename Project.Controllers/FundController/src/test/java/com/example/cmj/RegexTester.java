package com.example.cmj;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author mengjie_chen
 * @description 正则表达式测试类
 * @date 2021/1/4
 */
public class RegexTester {

    @Test
    public void testMapNull() {
        Map<String, List<String>> map = new HashMap<>();
        List<String> collect = Stream.of("aaa", "bbb", "ccc").collect(Collectors.toList());
        map.put(null, collect);
        List<String> strings = map.get(null);
        for (String string : strings) {
            System.out.println(string);
        }
    }

}
