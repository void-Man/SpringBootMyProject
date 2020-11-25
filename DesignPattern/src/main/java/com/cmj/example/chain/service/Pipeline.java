package com.cmj.example.chain.service;

public interface Pipeline<T> {

    /**
     * 尾插
     *
     * @param t
     * @return com.cmj.example.chain.service.Pipeline
     * @author mengjie_chen
     * @date 2020/11/25
     */
    Pipeline addLast(T t);

    /**
     * 获取链中第一个节点
     *
     * @param
     * @return T
     * @author mengjie_chen
     * @date 2020/11/25
     */
    T getHead();
}
