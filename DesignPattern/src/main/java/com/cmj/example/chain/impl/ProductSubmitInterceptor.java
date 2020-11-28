package com.cmj.example.chain.impl;

import com.cmj.example.chain.impl.abs.interceptor.AbstractDefaultOrderSubmitInterceptor;
import com.cmj.example.chain.service.OrderInvokeHandle;
import com.cmj.example.vo.DefaultSubmitOrderParamVo;
import com.cmj.example.vo.ResVo;
import com.cmj.example.vo.SubmitOrderContext;

import java.util.List;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public class ProductSubmitInterceptor extends AbstractDefaultOrderSubmitInterceptor {
    @Override
    protected ResVo checkParam(SubmitOrderContext<DefaultSubmitOrderParamVo> context) {
        System.out.println("into ProductCheckInterceptor");
        ResVo resVo = new ResVo();
        resVo.setSuccess(false);
        return resVo;
    }

    @Override
    public void addHandle(OrderInvokeHandle orderInvokeHandle) {

    }

    @Override
    public List<OrderInvokeHandle> getHandles() {
        return null;
    }
}
