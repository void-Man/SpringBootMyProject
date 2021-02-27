package com.cmj.example.vo;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/2/27
 */
public class EbuyDeliveryHasProductVo {
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品数量
     */
    private Integer productQty;
    /**
     * 运单商品ID
     */
    private String deliveryProductId;

    public String getDeliveryProductId() {
        return deliveryProductId;
    }

    public void setDeliveryProductId(String deliveryProductId) {
        this.deliveryProductId = deliveryProductId;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductQty() {
        return productQty;
    }

    public void setProductQty(Integer productQty) {
        this.productQty = productQty;
    }
}
