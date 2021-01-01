package com.cmj.example.strategy.reader;

public interface DataReader {

    /**
     * 读取数据
     *
     * @param source 数据来源，可能为文件路劲、网络路径或者数据库
     * @return java.lang.String 返回格式为json数据
     * @author mengjie_chen
     * @date 2021/1/1
     */
    String read(String source);

}
