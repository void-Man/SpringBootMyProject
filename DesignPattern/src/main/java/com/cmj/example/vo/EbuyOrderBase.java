package com.cmj.example.vo;

import java.math.BigDecimal;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/17
 */
public class EbuyOrderBase {

    private String orderId;
    private BigDecimal orderAmount;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }
}
