package com.cmj.example.vo;

import java.math.BigDecimal;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/3/6
 */
public class IndustryPositionVo {
    /**
     * 行业名称
     */
    private String industryName;
    /**
     * 行业占比
     */
    private BigDecimal rate;
    /**
     * 行业资金
     */
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
