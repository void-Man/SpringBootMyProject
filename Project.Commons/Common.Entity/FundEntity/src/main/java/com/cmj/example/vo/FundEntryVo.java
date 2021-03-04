package com.cmj.example.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/1/6
 */
@Data
public class FundEntryVo {

    /**
     * 购买时间
     */
    private String createTime;
    /**
     * 股票代码
     */
    private String stockNumber;
    /**
     * 股票名称
     */
    private String stockName;
    /**
     * 股票持仓数量（单位：股）
     */
    private Integer stockBuyQuantity;
    /**
     * 股票持仓金额（单位：元）
     */
    private BigDecimal stockBuyAmount;
    /**
     * 基金名称
     */
    private String fundName;

    public static final class FundEntryVoBuilder {
        private String createTime;
        private String stockNumber;
        private String stockName;
        private Integer stockBuyQuantity;
        private BigDecimal stockBuyAmount;
        private String fundName;

        private FundEntryVoBuilder() {
        }

        public static FundEntryVoBuilder fundEntryVo() {
            return new FundEntryVoBuilder();
        }

        public FundEntryVoBuilder createTime(String createTime) {
            this.createTime = createTime;
            return this;
        }

        public FundEntryVoBuilder stockNumber(String stockNumber) {
            this.stockNumber = stockNumber;
            return this;
        }

        public FundEntryVoBuilder stockName(String stockName) {
            this.stockName = stockName;
            return this;
        }

        public FundEntryVoBuilder stockBuyQuantity(Integer stockBuyQuantity) {
            this.stockBuyQuantity = stockBuyQuantity;
            return this;
        }

        public FundEntryVoBuilder stockBuyAmount(BigDecimal stockBuyAmount) {
            this.stockBuyAmount = stockBuyAmount;
            return this;
        }

        public FundEntryVoBuilder fundName(String fundName) {
            this.fundName = fundName;
            return this;
        }

        public FundEntryVo build() {
            FundEntryVo fundEntryVo = new FundEntryVo();
            fundEntryVo.setCreateTime(createTime);
            fundEntryVo.setStockNumber(stockNumber);
            fundEntryVo.setStockName(stockName);
            fundEntryVo.setStockBuyQuantity(stockBuyQuantity);
            fundEntryVo.setStockBuyAmount(stockBuyAmount);
            fundEntryVo.setFundName(fundName);
            return fundEntryVo;
        }
    }
}
