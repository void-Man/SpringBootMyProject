package com.cmj.example.chain.impl;

import com.cmj.example.chain.service.OrderInvokeHandle;
import com.cmj.example.vo.ResVo;

import java.util.List;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public class ProductSubmitInterceptor extends AbstractOrderSubmitInterceptor {
    @Override
    protected ResVo checkParam() {
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
