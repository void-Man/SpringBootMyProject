package com.cmj.example.chain.impl.abs;

import com.cmj.example.chain.service.OrderSubmitPipeline;

import java.util.Objects;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/11/24
 */
public abstract class AbstractOrderSubmitPipeline<T extends AbstractOrderSubmitInterceptor> implements OrderSubmitPipeline<T> {

    private final T head;
    private T tail;

    protected AbstractOrderSubmitPipeline(T head) {
        this.head = head;
    }

    @Override
    public OrderSubmitPipeline addLast(T t) {
        if (Objects.isNull(tail)) {
            tail = t;
            head.setNext(tail);
        } else {
            T temp = tail;
            tail = t;
            temp.setNext(tail);
        }
        return this;
    }

    @Override
    public T getHead() {
        return head;
    }
}
