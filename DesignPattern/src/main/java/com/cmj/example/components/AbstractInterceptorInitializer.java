package com.cmj.example.components;

import com.cmj.example.chain.impl.abs.interceptor.AbstractOrderSubmitInterceptor;
import com.cmj.example.chain.service.OrderInvokeHandler;
import com.cmj.example.vo.OrderResultVo;
import com.cmj.example.vo.SubmitOrderBaseParamVo;
import com.cmj.example.vo.SubmitOrderContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public abstract class AbstractInterceptorInitializer<E extends SubmitOrderBaseParamVo, T extends AbstractOrderSubmitInterceptor<E>> {
    private final List<T> checkerList = new ArrayList<>(10);

    public AbstractInterceptorInitializer<E, T> addLast(T checker) {
        checkerList.add(checker);
        return this;
    }

    public void check(SubmitOrderContext<E> context) {
        for (T checker : checkerList) {
            checker.check(context);
        }
    }

    public OrderResultVo invoke(E param) {
        for (T checker : checkerList) {
            OrderInvokeHandler<E> handler = checker.getHandler();
            handler.invoke(param);
        }
        return new OrderResultVo();
    }

}
