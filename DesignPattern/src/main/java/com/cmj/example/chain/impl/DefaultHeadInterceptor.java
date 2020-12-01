package com.cmj.example.chain.impl;

import com.cmj.example.chain.impl.abs.interceptor.AbstractDefaultOrderSubmitInterceptor;
import com.cmj.example.chain.service.OrderInvokeHandler;
import com.cmj.example.vo.DefaultSubmitOrderParamVo;
import com.cmj.example.vo.OrderResultVo;
import com.cmj.example.vo.SubmitOrderContext;

import java.util.List;

/**
 * @author mengjie_chen
 * @date 2020/11/24
 * @description 责任链头结点，不做任何业务处理，创建处理器List，供后面拦截器添加相应处理器
 */
public class DefaultHeadInterceptor extends AbstractDefaultOrderSubmitInterceptor {

    @Override
    protected void checkParam(SubmitOrderContext<DefaultSubmitOrderParamVo> context) {
        System.out.println("into DefaultHeadInterceptor");
    }

    @Override
    public OrderInvokeHandler<DefaultSubmitOrderParamVo> getHandler() {
        return null;
    }

}
