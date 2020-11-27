package com.cmj.example.chain.service;

import java.util.List;

public interface OrderInvokeHandleCreator {

    void addHandle(OrderInvokeHandle orderInvokeHandle);

    List<OrderInvokeHandle> getHandles();
}
