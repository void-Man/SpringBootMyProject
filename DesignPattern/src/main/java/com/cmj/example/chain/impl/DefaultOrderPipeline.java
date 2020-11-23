package com.cmj.example.chain.impl;

import com.cmj.example.chain.service.OrderPipeline;

import java.util.Objects;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/11/24
 */
public class DefaultOrderPipeline implements OrderPipeline {

    private AbstractOrderCheckInterceptor head;
    private AbstractOrderCheckInterceptor next;
    private AbstractOrderCheckInterceptor tail;

    @Override
    public OrderPipeline addLast(AbstractOrderCheckInterceptor orderCheckInterceptor) {
        if (Objects.isNull(head)) {
            head = orderCheckInterceptor;

            next = tail;
        } else if (Objects.isNull(tail)){
            tail = orderCheckInterceptor;

            next = null;
        } else {

        }
        return null;
    }
}
