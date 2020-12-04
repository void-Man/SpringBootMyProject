package com.cmj.example.chain.service;

import com.cmj.example.vo.SubmitOrderBaseParamVo;
import com.cmj.example.vo.SubmitOrderContext;

public interface OrderInvokeHandler<T extends SubmitOrderBaseParamVo> {

    void invoke(SubmitOrderContext<T> context);

}
