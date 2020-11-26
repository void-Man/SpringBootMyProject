package com.cmj.example.components;

import com.cmj.example.chain.service.OrderSubmitPipeline;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public abstract class AbstractInterceptorInitializer<T extends OrderSubmitPipeline, R> {
    protected final T pipeline;

    protected AbstractInterceptorInitializer(T pipeline) {
        this.pipeline = pipeline;
        intit(pipeline);
    }

    public abstract void intit(T t);


    public abstract R getHead();

}
