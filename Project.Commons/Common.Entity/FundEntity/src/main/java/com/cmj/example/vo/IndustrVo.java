package com.cmj.example.vo;

import lombok.Data;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/31
 */
@Data
public class IndustrVo {

    /**
     * 股票代码
     */
    private String stockNumber;
    /**
     * 行业名称
     */
    private String industrName;

    public static final class IndustrVoBuilder {
        private String stockNumber;
        private String industrName;

        private IndustrVoBuilder() {
        }

        public static IndustrVoBuilder industrVo() {
            return new IndustrVoBuilder();
        }

        public IndustrVoBuilder stockNumber(String stockNumber) {
            this.stockNumber = stockNumber;
            return this;
        }

        public IndustrVoBuilder industrName(String industrName) {
            this.industrName = industrName;
            return this;
        }

        public IndustrVo build() {
            IndustrVo industrVo = new IndustrVo();
            industrVo.setStockNumber(stockNumber);
            industrVo.setIndustrName(industrName);
            return industrVo;
        }
    }
}
