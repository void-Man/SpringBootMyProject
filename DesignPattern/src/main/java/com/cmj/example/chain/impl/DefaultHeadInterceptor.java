package com.cmj.example.chain.impl;

import com.cmj.example.chain.impl.abs.interceptor.AbstractDefaultOrderSubmitInterceptor;
import com.cmj.example.chain.service.OrderInvokeHandle;
import com.cmj.example.vo.ResVo;
import com.cmj.example.vo.SubmitOrderContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengjie_chen
 * @date 2020/11/24
 * @description 责任链头结点，不做任何业务处理，创建处理器List，供后面拦截器添加相应处理器
 */
public class DefaultHeadInterceptor extends AbstractDefaultOrderSubmitInterceptor {

    private final List<OrderInvokeHandle> handleList = new ArrayList<>(10);

    public DefaultHeadInterceptor() {

    }

    @Override
    protected ResVo checkParam(SubmitOrderContext context) {
        return new ResVo();
    }

    @Override
    public void addHandle(OrderInvokeHandle orderInvokeHandle) {

    }

    @Override
    public List<OrderInvokeHandle> getHandles() {
        return handleList;
    }
}
