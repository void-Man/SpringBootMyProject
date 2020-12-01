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
public abstract class AbstractInterceptorInitializer<P1 extends AbstractOrderSubmitInterceptor> {
    private final List<P1> checkerList = new ArrayList<>(10);

    public AbstractInterceptorInitializer<P1> addLast(P1 checker) {
        checkerList.add(checker);
        return this;
    }

    public List<P1> getCheckerList() {
        return checkerList;
    }

    public <P2 extends SubmitOrderBaseParamVo> void check(SubmitOrderContext<P2> context) {
        for (P1 checker : checkerList) {
            checker.check(context);
        }
    }

    public <P2 extends SubmitOrderBaseParamVo> OrderResultVo invoke(P2 param) {
        for (P1 checker : checkerList) {
            OrderInvokeHandler handler = checker.getHandler();
            handler.invoke(param);
        }
        return new OrderResultVo();
    }

}
