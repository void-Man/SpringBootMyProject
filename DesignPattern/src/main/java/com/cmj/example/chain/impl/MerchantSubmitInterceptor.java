package com.cmj.example.chain.impl;

import com.cmj.example.chain.impl.abs.interceptor.AbstractDefaultOrderSubmitInterceptor;
import com.cmj.example.chain.service.OrderInvokeHandler;
import com.cmj.example.vo.DefaultSubmitOrderParamVo;
import com.cmj.example.vo.OrderResultVo;
import com.cmj.example.vo.SubmitOrderContext;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public class MerchantSubmitInterceptor extends AbstractDefaultOrderSubmitInterceptor {
    @Override
    protected OrderResultVo checkParam(SubmitOrderContext<DefaultSubmitOrderParamVo> context) {
        System.out.println("into MerchantCheckInterceptor");
        return new OrderResultVo();
    }

    @Override
    public OrderInvokeHandler<DefaultSubmitOrderParamVo> getHandler() {
        return null;
    }
}
