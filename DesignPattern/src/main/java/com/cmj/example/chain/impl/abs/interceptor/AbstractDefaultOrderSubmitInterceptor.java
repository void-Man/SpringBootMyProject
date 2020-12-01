package com.cmj.example.chain.impl.abs.interceptor;

import com.cmj.example.chain.service.OrderInvokeHandle;
import com.cmj.example.vo.DefaultSubmitOrderParamVo;
import com.cmj.example.vo.OrderResultVo;
import com.cmj.example.vo.SubmitOrderContext;

/**
 * @author mengjie_chen
 * @description 普通商品提交订单拦截器
 * @date 2020/11/28
 */
public abstract class AbstractDefaultOrderSubmitInterceptor extends AbstractOrderSubmitInterceptor<DefaultSubmitOrderParamVo> {
    @Override
    protected abstract OrderResultVo checkParam(SubmitOrderContext<DefaultSubmitOrderParamVo> context);

    @Override
    public abstract OrderInvokeHandle<DefaultSubmitOrderParamVo> getCurrentHandle();
}
