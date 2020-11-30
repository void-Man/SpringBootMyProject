package com.cmj.example.chain.service;

import com.cmj.example.vo.OrderResultVo;
import com.cmj.example.vo.SubmitOrderBaseParamVo;
import com.cmj.example.vo.SubmitOrderContext;

public interface OrderSubmitInterceptor<T extends SubmitOrderBaseParamVo> {

    OrderResultVo check(SubmitOrderContext<T> context);

}
