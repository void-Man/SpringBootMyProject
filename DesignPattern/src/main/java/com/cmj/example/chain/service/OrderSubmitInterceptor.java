package com.cmj.example.chain.service;

import com.cmj.example.vo.ResVo;
import com.cmj.example.vo.SubmitOrderBaseParamVo;
import com.cmj.example.vo.SubmitOrderContext;

public interface OrderSubmitInterceptor<T extends SubmitOrderBaseParamVo> {

    ResVo check(SubmitOrderContext<T> context);

}
