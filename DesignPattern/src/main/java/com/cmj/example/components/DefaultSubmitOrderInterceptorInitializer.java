package com.cmj.example.components;

import com.cmj.example.chain.impl.abs.interceptor.AbstractDefaultOrderSubmitInterceptor;
import com.cmj.example.vo.DefaultSubmitOrderParamVo;
import com.cmj.example.vo.OrderResultVo;
import com.cmj.example.vo.SubmitOrderContext;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public class DefaultSubmitOrderInterceptorInitializer extends AbstractInterceptorInitializer<DefaultSubmitOrderParamVo, AbstractDefaultOrderSubmitInterceptor> {

    @Override
    public void check(SubmitOrderContext<DefaultSubmitOrderParamVo> context) {
        super.check(context);
    }

    @Override
    public OrderResultVo invoke(DefaultSubmitOrderParamVo param) {
        return super.invoke(param);
    }
}
