package com.cmj.example.chain.service;

import com.cmj.example.vo.OrderResultVo;
import com.cmj.example.vo.SubmitOrderBaseParamVo;
import com.cmj.example.vo.SubmitOrderContext;

public interface InterceptorInitializer<E extends SubmitOrderBaseParamVo> {

    void check(SubmitOrderContext<E> context);

    OrderResultVo invoke(SubmitOrderContext<E> context);

}
