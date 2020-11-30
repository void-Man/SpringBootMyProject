package com.cmj.example.chain.service;

import com.cmj.example.vo.SubmitOrderBaseParamVo;

import java.util.List;

public interface OrderInvokeHandleHolder<T extends SubmitOrderBaseParamVo> {

    OrderInvokeHandle<T> getCurrentHandle();

    List<OrderInvokeHandle<T>> getHandles();
}
