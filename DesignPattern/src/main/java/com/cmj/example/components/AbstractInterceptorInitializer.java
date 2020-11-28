package com.cmj.example.components;

import com.cmj.example.chain.impl.abs.AbstractOrderSubmitInterceptor;
import com.cmj.example.chain.impl.abs.AbstractOrderSubmitPipeline;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public abstract class AbstractInterceptorInitializer<T extends AbstractOrderSubmitPipeline, R extends AbstractOrderSubmitInterceptor> {
    protected final T pipeline;

    protected AbstractInterceptorInitializer(T pipeline) {
        this.pipeline = pipeline;
        intit(pipeline);
    }

    public abstract void intit(T t);


    public R getHead() {
        return (R) pipeline.getHead();
    }

}
