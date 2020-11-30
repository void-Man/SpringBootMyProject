package com.cmj.example.chain.impl.abs.interceptor;

import com.cmj.example.chain.service.OrderInvokeHandle;
import com.cmj.example.chain.service.OrderInvokeHandleHolder;
import com.cmj.example.chain.service.OrderSubmitInterceptor;
import com.cmj.example.vo.OrderResultVo;
import com.cmj.example.vo.SubmitOrderBaseParamVo;
import com.cmj.example.vo.SubmitOrderContext;

import java.util.List;
import java.util.Objects;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/11/24
 */
public abstract class AbstractOrderSubmitInterceptor<T extends SubmitOrderBaseParamVo> implements OrderSubmitInterceptor<T>, OrderInvokeHandleHolder {

    private OrderSubmitInterceptor next;

    @Override
    public OrderResultVo check(SubmitOrderContext<T> context) {
        OrderResultVo resVo = this.checkParam(context);
        if (resVo.isNeedHandle()) {
            context.getHandleList().add(getCurrentHandle(context.getT()));
        }
        if (resVo.isSuccess() && Objects.nonNull(next)) {
            resVo = next.check(context);
        }
        return resVo;
    }

    protected abstract OrderResultVo checkParam(SubmitOrderContext<T> context);

    public void setNext(OrderSubmitInterceptor next) {
        this.next = next;
    }

    public abstract OrderInvokeHandle getCurrentHandle(T param);

    @Override
    public List<OrderInvokeHandle> getHandles() {
        return null;
    }
}
