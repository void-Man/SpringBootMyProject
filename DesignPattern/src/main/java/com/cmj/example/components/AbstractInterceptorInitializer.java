package com.cmj.example.components;

import com.cmj.example.chain.impl.abs.interceptor.AbstractOrderSubmitInterceptor;
import com.cmj.example.chain.impl.abs.pileline.AbstractOrderSubmitPipeline;
import com.cmj.example.vo.SubmitOrderBaseParamVo;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public abstract class AbstractInterceptorInitializer<T extends AbstractOrderSubmitPipeline, R extends SubmitOrderBaseParamVo> {
    protected final T pipeline;

    protected AbstractInterceptorInitializer(T pipeline) {
        this.pipeline = pipeline;
        intit(pipeline);
    }

    public abstract void intit(T t);


    public AbstractOrderSubmitInterceptor<R> getHead() {
        return pipeline.getHead();
    }

}
