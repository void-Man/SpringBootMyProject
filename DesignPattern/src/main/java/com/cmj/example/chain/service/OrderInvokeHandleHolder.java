package com.cmj.example.chain.service;

import java.util.List;

public interface OrderInvokeHandleHolder {

    OrderInvokeHandle getCurrentHandle();

    List<OrderInvokeHandle> getHandles();
}
