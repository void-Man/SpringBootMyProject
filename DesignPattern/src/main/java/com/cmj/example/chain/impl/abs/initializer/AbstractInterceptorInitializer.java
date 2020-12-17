package com.cmj.example.chain.impl.abs.initializer;

import com.cmj.example.chain.service.InterceptorInitializer;
import com.cmj.example.chain.service.OrderInvokeHandler;
import com.cmj.example.chain.service.OrderSubmitChain;
import com.cmj.example.vo.OrderResultVo;
import com.cmj.example.vo.SubmitOrderBaseParamVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public abstract class AbstractInterceptorInitializer<T extends OrderSubmitChain<E>, E extends SubmitOrderBaseParamVo> implements InterceptorInitializer<E> {
    private final List<T> checkerList = new ArrayList<>(10);

    public AbstractInterceptorInitializer<T, E> addLast(T checker) {
        checkerList.add(checker);
        return this;
    }

    @Override
    public void check(E param) {
        for (T checker : checkerList) {
            checker.check(param);
        }
    }

    @Override
    public OrderResultVo invoke(E param) {
        for (T checker : checkerList) {
            OrderInvokeHandler<E> handler = checker.create();
            handler.invoke(param);
        }
        return new OrderResultVo();
    }

}
