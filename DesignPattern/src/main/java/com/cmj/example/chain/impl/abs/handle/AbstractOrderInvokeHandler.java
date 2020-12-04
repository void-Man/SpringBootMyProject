package com.cmj.example.chain.impl.abs.handle;

import com.cmj.example.chain.service.OrderInvokeHandler;
import com.cmj.example.vo.SubmitOrderBaseParamVo;
import com.cmj.example.vo.SubmitOrderContext;

/**
 * @author mengjie_chen
 * @description date 2020/11/30
 */
public abstract class AbstractOrderInvokeHandler<T extends SubmitOrderBaseParamVo> implements OrderInvokeHandler<T> {

    @Override
    public void invoke(SubmitOrderContext<T> context) {

    }
}
