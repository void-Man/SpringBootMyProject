package com.cmj.example.components;

import com.cmj.example.chain.impl.DefaultHeadInterceptor;
import com.cmj.example.chain.impl.abs.AbstractDefaultOrderSubmitInterceptor;
import com.cmj.example.chain.impl.abs.AbstractOrderSubmitPipeline;
import com.cmj.example.chain.impl.abs.DefaultOrderSubmitPipeline;
import com.cmj.example.chain.service.OrderSubmitInterceptor;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public abstract class DefaultSubmitOrderInterceptorInitializer extends AbstractInterceptorInitializer<AbstractOrderSubmitPipeline, AbstractDefaultOrderSubmitInterceptor> {

    public DefaultSubmitOrderInterceptorInitializer() {
        super(new DefaultOrderSubmitPipeline(new DefaultHeadInterceptor()));
    }
}
