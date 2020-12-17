package com.cmj.example.chain.impl;

import com.cmj.example.chain.impl.abs.interceptor.AbstractDefaultOrderSubmitChain;
import com.cmj.example.chain.service.OrderInvokeHandler;
import com.cmj.example.vo.DefaultSubmitOrderParamVo;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public class ProductSubmitChain extends AbstractDefaultOrderSubmitChain {
    @Override
    protected void checkParam(DefaultSubmitOrderParamVo paramVo) {
        System.out.println("into ProductCheckInterceptor");
    }

    @Override
    public OrderInvokeHandler<DefaultSubmitOrderParamVo> create() {
        return null;
    }
}
