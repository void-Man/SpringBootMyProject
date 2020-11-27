package com.cmj.example.chain.impl;

import com.cmj.example.chain.service.OrderInvokeHandle;
import com.cmj.example.chain.service.OrderInvokeHandleCreator;
import com.cmj.example.chain.service.OrderSubmitInterceptor;
import com.cmj.example.vo.ResVo;

import java.util.List;
import java.util.Objects;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/11/24
 */
public abstract class AbstractOrderSubmitInterceptor implements OrderSubmitInterceptor, OrderInvokeHandleCreator {

    private OrderSubmitInterceptor next;

    @Override
    public ResVo check() {
        ResVo resVo = this.checkParam();
        if (resVo.isSuccess() && Objects.nonNull(next)) {
            resVo = next.check();
        }
        return resVo;
    }

    protected abstract ResVo checkParam();

    public void setNext(OrderSubmitInterceptor next) {
        this.next = next;
    }

    @Override
    public abstract void addHandle(OrderInvokeHandle orderInvokeHandle);

    @Override
    public abstract List<OrderInvokeHandle> getHandles();
}
