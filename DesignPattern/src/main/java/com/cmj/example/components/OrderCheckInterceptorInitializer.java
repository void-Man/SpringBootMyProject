package com.cmj.example.components;

import com.cmj.example.chain.impl.OrderCheckPipeline;
import com.cmj.example.chain.service.IOrderCheckInterceptor;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public abstract class OrderCheckInterceptorInitializer extends AbstractInterceptorInitializer<OrderCheckPipeline> {

    public OrderCheckInterceptorInitializer() {
        super(new OrderCheckPipeline());
    }

    public IOrderCheckInterceptor getHead() {
        return pipeline.getHead();
    }
}
