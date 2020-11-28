package com.cmj.example.vo;

import com.cmj.example.chain.service.OrderInvokeHandle;

import java.util.List;

/**
 * @author mengjie_chen
 * @description 提交订单上下文类
 * @date 2020/11/28
 */
public class SubmitOrderContext<T extends SubmitOrderBaseParamVo> {

    private List<OrderInvokeHandle> handleList;

    private T t;

    public List<OrderInvokeHandle> getHandleList() {
        return handleList;
    }

    public void setHandleList(List<OrderInvokeHandle> handleList) {
        this.handleList = handleList;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
