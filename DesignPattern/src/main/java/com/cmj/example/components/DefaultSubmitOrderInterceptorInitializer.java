package com.cmj.example.components;

import com.cmj.example.chain.impl.abs.interceptor.AbstractDefaultOrderSubmitInterceptor;
import com.cmj.example.vo.OrderResultVo;
import com.cmj.example.vo.SubmitOrderBaseParamVo;
import com.cmj.example.vo.SubmitOrderContext;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public class DefaultSubmitOrderInterceptorInitializer extends AbstractInterceptorInitializer<AbstractDefaultOrderSubmitInterceptor> {

    @Override
    public AbstractInterceptorInitializer<AbstractDefaultOrderSubmitInterceptor> addLast(AbstractDefaultOrderSubmitInterceptor checker) {
        return super.addLast(checker);
    }

    @Override
    public <P2 extends SubmitOrderBaseParamVo> void check(SubmitOrderContext<P2> context) {
        super.check(context);
    }

    @Override
    public <P2 extends SubmitOrderBaseParamVo> OrderResultVo invoke(P2 param) {
        return super.invoke(param);
    }
}
