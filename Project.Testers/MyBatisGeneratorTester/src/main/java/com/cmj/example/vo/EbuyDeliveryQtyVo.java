package com.cmj.example.vo;

import java.io.Serializable;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/2/27
 */
public class EbuyDeliveryQtyVo implements Serializable {
    private static final long serialVersionUID = -8025807597501412779L;

    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 商品购买数量
     */
    private Integer productQty;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getProductQty() {
        return productQty;
    }

    public void setProductQty(Integer productQty) {
        this.productQty = productQty;
    }
}
