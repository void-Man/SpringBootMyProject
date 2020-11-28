package com.cmj.example.chain.impl.abs;

import com.cmj.example.chain.service.OrderInvokeHandle;
import com.cmj.example.chain.service.OrderInvokeHandleCreator;
import com.cmj.example.chain.service.OrderSubmitInterceptor;
import com.cmj.example.vo.ResVo;
import com.cmj.example.vo.SubmitOrderBaseParamVo;
import com.cmj.example.vo.SubmitOrderContext;

import java.util.List;
import java.util.Objects;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/11/24
 */
public abstract class AbstractOrderSubmitInterceptor<T extends SubmitOrderBaseParamVo> implements OrderSubmitInterceptor<T>, OrderInvokeHandleCreator {

    private OrderSubmitInterceptor<T> next;

    @Override
    public ResVo check(SubmitOrderContext<T> context) {
        ResVo resVo = this.checkParam(context);
        if (resVo.isSuccess() && Objects.nonNull(next)) {
            resVo = next.check(context);
        }
        return resVo;
    }

    protected abstract ResVo checkParam(SubmitOrderContext<T> context);

    public void setNext(OrderSubmitInterceptor<T> next) {
        this.next = next;
    }

    @Override
    public abstract void addHandle(OrderInvokeHandle orderInvokeHandle);

    @Override
    public abstract List<OrderInvokeHandle> getHandles();
}
