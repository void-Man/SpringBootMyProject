package com.cmj.example.chain.impl;

import com.cmj.example.chain.impl.abs.interceptor.AbstractDefaultOrderSubmitInterceptor;
import com.cmj.example.chain.service.OrderInvokeHandler;
import com.cmj.example.vo.DefaultSubmitOrderParamVo;
import com.cmj.example.vo.SubmitOrderContext;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public class ProductSubmitInterceptor extends AbstractDefaultOrderSubmitInterceptor {
    @Override
    protected void checkParam(SubmitOrderContext<DefaultSubmitOrderParamVo> context) {
        System.out.println("into ProductCheckInterceptor");
    }

    @Override
    public OrderInvokeHandler<DefaultSubmitOrderParamVo> create() {
        return null;
    }
}
