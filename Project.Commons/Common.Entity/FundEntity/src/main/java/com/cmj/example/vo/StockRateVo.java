package com.cmj.example.vo;

import java.math.BigDecimal;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/3/6
 */
public class StockRateVo {

    /**
     * 股票名称
     */
    private String name;
    /**
     * 股票代码
     */
    private String code;
    /**
     * 股票持仓数量（股）
     */
    private Integer count;
    /**
     * 股票持仓金额（元）
     */
    private BigDecimal total_assets;
    /**
     * 股票持仓比例
     */
    private BigDecimal ratio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getTotal_assets() {
        return total_assets;
    }

    public void setTotal_assets(BigDecimal total_assets) {
        this.total_assets = total_assets;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }
}
