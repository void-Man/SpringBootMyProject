package com.example.cmj;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        String content = "jQuery111102605675353179122_1615003191043({\n" +
                "    \"code\": 0,\n" +
                "    \"msg\": \"OK\",\n" +
                "    \"data\": {\n" +
                "        \"symbol\": \"jj519066\",\n" +
                "        \"date\": \"2020-12-31\",\n" +
                "        \"type\": 4,\n" +
                "        \"data\": {\n" +
                "            \"peizhi\": [\n" +
                "                {\n" +
                "                    \"name\": \"股票\",\n" +
                "                    \"ratio\": \"74.73\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"债券\",\n" +
                "                    \"ratio\": \"0.04\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"现金\",\n" +
                "                    \"ratio\": \"25.86\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"其他\",\n" +
                "                    \"ratio\": \"0.00\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"hangye\": [\n" +
                "                {\n" +
                "                    \"name\": \"制造业\",\n" +
                "                    \"ratio\": \"49.95\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"租赁和商务服务业\",\n" +
                "                    \"ratio\": \"9.90\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"文化、体育和娱乐业\",\n" +
                "                    \"ratio\": \"6.07\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"信息传输、软件和信息\",\n" +
                "                    \"ratio\": \"3.38\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"zhongcang\": [\n" +
                "                {\n" +
                "                    \"name\": \"贵州茅台\",\n" +
                "                    \"code\": \"600519\",\n" +
                "                    \"count\": \"515025\",\n" +
                "                    \"total_assets\": \"1029020032.00\",\n" +
                "                    \"ratio\": \"10.15\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"五粮液\",\n" +
                "                    \"code\": \"000858\",\n" +
                "                    \"count\": \"2699959\",\n" +
                "                    \"total_assets\": \"787982976.00\",\n" +
                "                    \"ratio\": \"7.77\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"中国中免\",\n" +
                "                    \"code\": \"601888\",\n" +
                "                    \"count\": \"2100471\",\n" +
                "                    \"total_assets\": \"593278016.00\",\n" +
                "                    \"ratio\": \"5.85\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"良信股份\",\n" +
                "                    \"code\": \"002706\",\n" +
                "                    \"count\": \"15620392\",\n" +
                "                    \"total_assets\": \"478608992.00\",\n" +
                "                    \"ratio\": \"4.72\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"百润股份\",\n" +
                "                    \"code\": \"002568\",\n" +
                "                    \"count\": \"4000000\",\n" +
                "                    \"total_assets\": \"417160000.00\",\n" +
                "                    \"ratio\": \"4.11\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"宋城演艺\",\n" +
                "                    \"code\": \"300144\",\n" +
                "                    \"count\": \"23500000\",\n" +
                "                    \"total_assets\": \"416420000.00\",\n" +
                "                    \"ratio\": \"4.11\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"南极电商\",\n" +
                "                    \"code\": \"002127\",\n" +
                "                    \"count\": \"30000000\",\n" +
                "                    \"total_assets\": \"410400000.00\",\n" +
                "                    \"ratio\": \"4.05\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"金山办公\",\n" +
                "                    \"code\": \"688111\",\n" +
                "                    \"count\": \"900000\",\n" +
                "                    \"total_assets\": \"341460992.00\",\n" +
                "                    \"ratio\": \"3.37\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"迈瑞医疗\",\n" +
                "                    \"code\": \"300760\",\n" +
                "                    \"count\": \"800000\",\n" +
                "                    \"total_assets\": \"340800000.00\",\n" +
                "                    \"ratio\": \"3.36\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"安井食品\",\n" +
                "                    \"code\": \"603345\",\n" +
                "                    \"count\": \"1700041\",\n" +
                "                    \"total_assets\": \"327887008.00\",\n" +
                "                    \"ratio\": \"3.23\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    }\n" +
                "})";
        String pat = "jQuery(.*\\()";
        Pattern pattern = Pattern.compile(pat);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()){
            String all = matcher.replaceAll("");
            System.out.println(all);
        }
    }

}
