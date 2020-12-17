package com.cmj.example.chain.service;

import com.cmj.example.vo.SubmitOrderBaseParamVo;

public interface OrderInvokeHandler<T extends SubmitOrderBaseParamVo> {

    void handle(T param);

}
