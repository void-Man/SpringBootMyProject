package com.cmj.example.chain.impl;

import com.cmj.example.chain.impl.abs.initializer.AbstractInterceptorInitializer;
import com.cmj.example.chain.impl.abs.interceptor.AbstractDefaultOrderSubmitChain;
import com.cmj.example.vo.DefaultSubmitOrderParamVo;
import com.cmj.example.vo.OrderResultVo;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public class DefaultSubmitOrderInterceptorInitializer extends AbstractInterceptorInitializer<AbstractDefaultOrderSubmitChain, DefaultSubmitOrderParamVo> {

    @Override
    public void check(DefaultSubmitOrderParamVo paramVo) {
        super.check(paramVo);
    }

    @Override
    public OrderResultVo invoke(DefaultSubmitOrderParamVo paramVo) {
        return super.invoke(paramVo);
    }
}
