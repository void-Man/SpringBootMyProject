package com.cmj.example.vo;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/2/27
 */
public class EbuyOrderHasProductFlagMapVo {
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 项目ID
     */
    private String projectId;
    /**
     * 购买人ID
     */
    private String buyerId;
    /**
     * 订单商品ID
     */
    private String hasProductId;
    /**
     * 商品ID
     */
    private String productId;
    /**
     * 商品图片
     */
    private String productPic;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品数量
     */
    private Integer productQty;

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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getHasProductId() {
        return hasProductId;
    }

    public void setHasProductId(String hasProductId) {
        this.hasProductId = hasProductId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
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
