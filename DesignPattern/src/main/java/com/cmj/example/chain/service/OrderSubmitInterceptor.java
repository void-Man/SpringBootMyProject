package com.cmj.example.chain.service;

import com.cmj.example.vo.SubmitOrderBaseParamVo;
import com.cmj.example.vo.SubmitOrderContext;

public interface OrderSubmitInterceptor<T extends SubmitOrderBaseParamVo> {

    void check(SubmitOrderContext<T> context);

    OrderInvokeHandler<T> invocationHandler();
}
