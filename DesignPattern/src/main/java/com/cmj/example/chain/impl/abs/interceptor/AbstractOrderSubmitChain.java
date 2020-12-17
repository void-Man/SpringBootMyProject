package com.cmj.example.chain.impl.abs.interceptor;

import com.cmj.example.chain.service.OrderInvokeHandler;
import com.cmj.example.chain.service.OrderSubmitChain;
import com.cmj.example.vo.SubmitOrderBaseParamVo;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/11/24
 */
public abstract class AbstractOrderSubmitChain<T extends SubmitOrderBaseParamVo> implements OrderSubmitChain<T> {

    @Override
    public void check(T param) {
        this.checkParam(param);
    }

    protected abstract void checkParam(T param);

    @Override
    public abstract OrderInvokeHandler<T> create();
}
