package com.cmj.example.components;

import com.cmj.example.chain.impl.DefaultHeadInterceptor;
import com.cmj.example.chain.impl.abs.pileline.DefaultOrderSubmitPipeline;
import com.cmj.example.vo.SubmitOrderBaseParamVo;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public abstract class DefaultSubmitOrderInterceptorInitializer extends AbstractInterceptorInitializer<DefaultOrderSubmitPipeline, SubmitOrderBaseParamVo> {

    public DefaultSubmitOrderInterceptorInitializer() {
        super(new DefaultOrderSubmitPipeline(new DefaultHeadInterceptor()));
    }
}
