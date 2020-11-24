package com.cmj.example.chain.impl;

import com.cmj.example.chain.service.Pipeline;

import java.util.Objects;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/11/24
 */
public class DefaultPipeline implements Pipeline<AbstractOrderCheckInterceptor> {

    private AbstractOrderCheckInterceptor head;
    private AbstractOrderCheckInterceptor tail;

    @Override
    public Pipeline addLast(AbstractOrderCheckInterceptor orderCheckInterceptor) {
        if (Objects.isNull(head)) {
            head = orderCheckInterceptor;
            head.setNext(tail);
        } else if (Objects.isNull(tail)) {
            tail = orderCheckInterceptor;
            head.setNext(tail);
        } else {
            AbstractOrderCheckInterceptor temp = tail;
            tail = orderCheckInterceptor;
            temp.setNext(tail);
        }
        return this;
    }

    public static void main(String[] args) {
        MerchantCheckInterceptor merchantCheckInterceptor = new MerchantCheckInterceptor();
        MerchantCheckInterceptor merchantCheckInterceptor1 = new MerchantCheckInterceptor();
        MerchantCheckInterceptor merchantCheckInterceptor2 = new MerchantCheckInterceptor();
        MerchantCheckInterceptor merchantCheckInterceptor3 = new MerchantCheckInterceptor();
        Pipeline pipeline = new DefaultPipeline().
                addLast(merchantCheckInterceptor)
                .addLast(merchantCheckInterceptor1)
                .addLast(merchantCheckInterceptor2)
                .addLast(merchantCheckInterceptor3);
        System.out.println(pipeline);
    }
}
