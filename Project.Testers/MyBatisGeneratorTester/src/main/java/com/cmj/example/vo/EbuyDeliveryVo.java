package com.cmj.example.vo;

import java.util.List;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/2/27
 */
public class EbuyDeliveryVo {
    /**
     * 运单ID
     */
    private String deliveryId;
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 快递单号
     */
    private String expressNumber;
    /**
     * 快递商品信息
     */
    private List<EbuyDeliveryHasProductVo> ebuyDeliveryHasProductVoList;

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public List<EbuyDeliveryHasProductVo> getEbuyDeliveryHasProductVoList() {
        return ebuyDeliveryHasProductVoList;
    }

    public void setEbuyDeliveryHasProductVoList(List<EbuyDeliveryHasProductVo> ebuyDeliveryHasProductVoList) {
        this.ebuyDeliveryHasProductVoList = ebuyDeliveryHasProductVoList;
    }
}