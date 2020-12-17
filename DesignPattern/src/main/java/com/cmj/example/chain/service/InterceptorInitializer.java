package com.cmj.example.chain.service;

import com.cmj.example.vo.OrderResultVo;
import com.cmj.example.vo.SubmitOrderBaseParamVo;

public interface InterceptorInitializer<T extends SubmitOrderBaseParamVo> {

    void check(T param);

    OrderResultVo invoke(T param);

}
