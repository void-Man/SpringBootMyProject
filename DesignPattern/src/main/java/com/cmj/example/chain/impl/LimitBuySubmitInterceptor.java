package com.cmj.example.chain.impl;

import com.cmj.example.chain.service.OrderInvokeHandle;
import com.cmj.example.vo.ResVo;
import com.cmj.example.vo.SubmitOrderContext;

import java.util.List;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public class LimitBuySubmitInterceptor extends AbstractDefaultOrderSubmitInterceptor {
    @Override
    protected ResVo checkParam(SubmitOrderContext context) {
        System.out.println("into LimitBuyCheckInterceptor");
        return new ResVo();
    }

    @Override
    public void addHandle(OrderInvokeHandle orderInvokeHandle) {

    }

    @Override
    public List<OrderInvokeHandle> getHandles() {
        return null;
    }
}
