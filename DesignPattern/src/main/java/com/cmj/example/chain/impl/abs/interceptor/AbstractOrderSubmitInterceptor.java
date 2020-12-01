package com.cmj.example.chain.impl.abs.interceptor;

import com.cmj.example.chain.service.OrderInvokeHandler;
import com.cmj.example.chain.service.OrderSubmitInterceptor;
import com.cmj.example.vo.OrderResultVo;
import com.cmj.example.vo.SubmitOrderBaseParamVo;
import com.cmj.example.vo.SubmitOrderContext;

import java.util.Objects;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/11/24
 */
public abstract class AbstractOrderSubmitInterceptor<T extends SubmitOrderBaseParamVo> implements OrderSubmitInterceptor<T> {

    private OrderSubmitInterceptor next;

    @Override
    public OrderResultVo check(SubmitOrderContext<T> context) {
        OrderResultVo resVo = this.checkParam(context);
        if (resVo.isNeedHandle()) {
            context.getHandleList().add(getHandler());
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

    @Override
    public abstract OrderInvokeHandler<T> getHandler();
}
