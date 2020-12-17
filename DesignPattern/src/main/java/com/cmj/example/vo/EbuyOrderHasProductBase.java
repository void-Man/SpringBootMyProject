package com.cmj.example.vo;

import java.math.BigDecimal;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/17
 */
public class EbuyOrderHasProductBase {

    private BigDecimal productAmount;
    private BigDecimal realAmount;

    public BigDecimal getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(BigDecimal productAmount) {
        this.productAmount = productAmount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }
}
