package com.cmj.example.chain.service;

import com.cmj.example.vo.SubmitOrderBaseParamVo;

public interface OrderSubmitChain<T extends SubmitOrderBaseParamVo> {

    void check(T param);

    OrderInvokeHandler<T> create();
}
