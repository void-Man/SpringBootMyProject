package com.cmj.example.vo;

import java.math.BigDecimal;

/**
 * @author mengjie_chen
 * @description 股票行业分布result vo
 * @date 2021/3/6
 */
public class StockIndustryResultVo {

    /**
     * 基金名称
     */
    private String fundName;
    /**
     * 基金代码
     */
    private String fundNumber;
    /**
     * 股票名称
     */
    private String stockName;
    /**
     * 股票代码
     */
    private String stockNumber;
    /**
     * 行业名称
     */
    private String industryName;
    /**
     * 股票购买金额
     */
    private BigDecimal amount;

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getFundNumber() {
        return fundNumber;
    }

    public void setFundNumber(String fundNumber) {
        this.fundNumber = fundNumber;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(String stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
