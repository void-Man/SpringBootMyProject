package com.cmj.example.chain.impl.abs;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/11/28
 */
public class DefaultOrderSubmitPipeline extends AbstractOrderSubmitPipeline<AbstractDefaultOrderSubmitInterceptor>{
    public DefaultOrderSubmitPipeline(AbstractDefaultOrderSubmitInterceptor head) {
        super(head);
    }
}
