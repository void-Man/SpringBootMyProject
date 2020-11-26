package com.cmj.example.components;

import com.cmj.example.chain.impl.OrderCheckOrderSubmitPipeline;
import com.cmj.example.chain.service.IOrderSubmitInterceptor;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public abstract class OrderCheckInterceptorInitializer extends AbstractInterceptorInitializer<OrderCheckOrderSubmitPipeline, IOrderSubmitInterceptor> {

    public OrderCheckInterceptorInitializer() {
        super(new OrderCheckOrderSubmitPipeline());
    }

    public IOrderSubmitInterceptor getHead() {
        return pipeline.getHead();
    }
}
