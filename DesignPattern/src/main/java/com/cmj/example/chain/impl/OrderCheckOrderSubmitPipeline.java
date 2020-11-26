package com.cmj.example.chain.impl;

import com.cmj.example.chain.service.OrderSubmitPipeline;

import java.util.Objects;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/11/24
 */
public class OrderCheckOrderSubmitPipeline implements OrderSubmitPipeline<AbstractOrderSubmitInterceptor> {

    private AbstractOrderSubmitInterceptor head;
    private AbstractOrderSubmitInterceptor tail;

    @Override
    public OrderSubmitPipeline addLast(AbstractOrderSubmitInterceptor orderCheckInterceptor) {
        if (Objects.isNull(head)) {
            head = orderCheckInterceptor;
            head.setNext(tail);
        } else if (Objects.isNull(tail)) {
            tail = orderCheckInterceptor;
            head.setNext(tail);
        } else {
            AbstractOrderSubmitInterceptor temp = tail;
            tail = orderCheckInterceptor;
            temp.setNext(tail);
        }
        return this;
    }

    @Override
    public AbstractOrderSubmitInterceptor getHead() {
        return head;
    }

    public static void main(String[] args) {
        MerchantSubmitInterceptor merchantCheckInterceptor = new MerchantSubmitInterceptor();
        MerchantSubmitInterceptor merchantCheckInterceptor1 = new MerchantSubmitInterceptor();
        MerchantSubmitInterceptor merchantCheckInterceptor2 = new MerchantSubmitInterceptor();
        MerchantSubmitInterceptor merchantCheckInterceptor3 = new MerchantSubmitInterceptor();
        @SuppressWarnings("unchecked")
        OrderSubmitPipeline orderSubmitPipeline = new OrderCheckOrderSubmitPipeline().
                addLast(merchantCheckInterceptor)
                .addLast(merchantCheckInterceptor1)
                .addLast(merchantCheckInterceptor2)
                .addLast(merchantCheckInterceptor3);
        System.out.println(orderSubmitPipeline);
    }
}
