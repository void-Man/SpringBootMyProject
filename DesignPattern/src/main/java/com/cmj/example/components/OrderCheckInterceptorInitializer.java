package com.cmj.example.components;

import com.cmj.example.chain.impl.OrderCheckOrderSubmitPipeline;
import com.cmj.example.chain.service.OrderSubmitInterceptor;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public abstract class OrderCheckInterceptorInitializer extends AbstractInterceptorInitializer<OrderCheckOrderSubmitPipeline, OrderSubmitInterceptor> {

    public OrderCheckInterceptorInitializer() {
        super(new OrderCheckOrderSubmitPipeline());
    }

    public OrderSubmitInterceptor getHead() {
        return pipeline.getHead();
    }
}
