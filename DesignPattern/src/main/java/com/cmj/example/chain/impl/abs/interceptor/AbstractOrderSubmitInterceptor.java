package com.cmj.example.chain.impl.abs.interceptor;

import com.cmj.example.chain.service.OrderInvokeHandler;
import com.cmj.example.chain.service.OrderSubmitInterceptor;
import com.cmj.example.vo.SubmitOrderBaseParamVo;
import com.cmj.example.vo.SubmitOrderContext;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/11/24
 */
public abstract class AbstractOrderSubmitInterceptor<T extends SubmitOrderBaseParamVo> implements OrderSubmitInterceptor<T> {


    @Override
    public void check(SubmitOrderContext<T> context) {
        this.checkParam(context);
    }

    protected abstract void checkParam(SubmitOrderContext<T> context);

    @Override
    public abstract OrderInvokeHandler<T> getHandler();
}
