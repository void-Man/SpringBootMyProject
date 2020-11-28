package com.cmj.example.chain.impl.abs.pileline;

import com.cmj.example.chain.impl.abs.interceptor.AbstractDefaultOrderSubmitInterceptor;

/**
 * @author mengjie_chen
 * @description   默认提交订单流水线
 * @date 2020/11/28
 */
public class DefaultOrderSubmitPipeline extends AbstractOrderSubmitPipeline<AbstractDefaultOrderSubmitInterceptor>{
    public DefaultOrderSubmitPipeline(AbstractDefaultOrderSubmitInterceptor head) {
        super(head);
    }
}
