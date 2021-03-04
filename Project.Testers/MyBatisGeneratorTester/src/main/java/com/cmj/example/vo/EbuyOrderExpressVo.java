package com.cmj.example.vo;

import java.io.Serializable;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/2/27
 */
public class EbuyOrderExpressVo implements Serializable {
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 收货人
     */
    private String deliverName;
    /**
     * 收货人电话
     */
    private String deliverPhone;
    /**
     * 收货人区域地址
     */
    private String deliverArea;
    /**
     * 收货人详细地址
     */
    private String deliverDetail;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品数量
     */
    private Integer productQty;
    /**
     * 是否拆分包裹
     */
    private Integer isSplit;
    /**
     * 快递公司
     */
    private String expressCompany;
    /**
     * 快递单号
     */
    private String expressNumber;
    /**
     * 数据是否正确
     */
    private Integer success = 1;
    /**
     * 备注
     */
    private String remark;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getDeliverName() {
        return deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName;
    }

    public String getDeliverPhone() {
        return deliverPhone;
    }

    public void setDeliverPhone(String deliverPhone) {
        this.deliverPhone = deliverPhone;
    }

    public String getDeliverArea() {
        return deliverArea;
    }

    public void setDeliverArea(String deliverArea) {
        this.deliverArea = deliverArea;
    }

    public String getDeliverDetail() {
        return deliverDetail;
    }

    public void setDeliverDetail(String deliverDetail) {
        this.deliverDetail = deliverDetail;
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

    public Integer getIsSplit() {
        return isSplit;
    }

    public void setIsSplit(Integer isSplit) {
        this.isSplit = isSplit;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
