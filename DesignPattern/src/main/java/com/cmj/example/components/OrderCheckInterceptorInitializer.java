package com.cmj.example.components;

import com.cmj.example.chain.impl.OrderCheckPipeline;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public abstract class OrderCheckInterceptorInitializer extends AbstractInterceptorInitializer<OrderCheckPipeline>{

    @Override
    protected OrderCheckPipeline getPipeLine() {
        return new OrderCheckPipeline();
    }
}
