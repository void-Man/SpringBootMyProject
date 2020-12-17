package com.cmj.example.chain.impl.abs.handle;

import com.cmj.example.chain.service.OrderInvokeHandler;
import com.cmj.example.vo.EbuyOrderBase;
import com.cmj.example.vo.SubmitOrderBaseParamVo;

/**
 * @author mengjie_chen
 * @description date 2020/11/30
 */
public abstract class AbstractOrderInvokeHandler<T extends SubmitOrderBaseParamVo> implements OrderInvokeHandler<T> {

    @Override
    public void handle(T param) {
        EbuyOrderBase ebuyOrderBase = param.getEbuyOrderBase();

    }
}
