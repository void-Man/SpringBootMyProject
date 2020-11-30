package com.cmj.example.vo;

import com.cmj.example.chain.service.OrderInvokeHandle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengjie_chen
 * @description 提交订单上下文类
 * @date 2020/11/28
 */
public class SubmitOrderContext<T extends SubmitOrderBaseParamVo> {

    private final List<OrderInvokeHandle> handleList = new ArrayList<>(10);

    private T param;

    public List<OrderInvokeHandle> getHandleList() {
        return handleList;
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }
}
