package com.cmj.example.chain.impl.abs.initializer;

import com.cmj.example.chain.service.InterceptorInitializer;
import com.cmj.example.chain.service.OrderInvokeHandler;
import com.cmj.example.chain.service.OrderSubmitInterceptor;
import com.cmj.example.vo.OrderResultVo;
import com.cmj.example.vo.SubmitOrderBaseParamVo;
import com.cmj.example.vo.SubmitOrderContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public abstract class AbstractInterceptorInitializer<T extends OrderSubmitInterceptor<E>, E extends SubmitOrderBaseParamVo> implements InterceptorInitializer<E> {
    private final List<T> checkerList = new ArrayList<>(10);

    public AbstractInterceptorInitializer<T, E> addLast(T checker) {
        checkerList.add(checker);
        return this;
    }

    @Override
    public void check(SubmitOrderContext<E> context) {
        for (T checker : checkerList) {
            checker.check(context);
        }
    }

    @Override
    public OrderResultVo invoke(SubmitOrderContext<E> context) {
        for (T checker : checkerList) {
            OrderInvokeHandler<E> handler = checker.create();
            handler.invoke(context);
        }
        return new OrderResultVo();
    }

}
