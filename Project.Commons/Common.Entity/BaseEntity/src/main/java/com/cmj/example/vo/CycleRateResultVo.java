package com.cmj.example.vo;

import java.math.BigDecimal;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/3/4
 */
public class CycleRateResultVo {
    /**
     * 本周期长度
     */
    private BigDecimal cycleRate;
    /**
     * 本周期第一天
     */
    private String cycleFirstDay;
    /**
     * 本周期最后一天
     */
    private String cycleLastDay;

    public BigDecimal getCycleRate() {
        return cycleRate;
    }

    public void setCycleRate(BigDecimal cycleRate) {
        this.cycleRate = cycleRate;
    }

    public String getCycleFirstDay() {
        return cycleFirstDay;
    }

    public void setCycleFirstDay(String cycleFirstDay) {
        this.cycleFirstDay = cycleFirstDay;
    }

    public String getCycleLastDay() {
        return cycleLastDay;
    }

    public void setCycleLastDay(String cycleLastDay) {
        this.cycleLastDay = cycleLastDay;
    }


    public static final class CycleRateResultVoBuilder {
        private BigDecimal cycleRate;
        private String cycleFirstDay;
        private String cycleLastDay;

        private CycleRateResultVoBuilder() {
        }

        public static CycleRateResultVoBuilder cycleRateResultVo() {
            return new CycleRateResultVoBuilder();
        }

        public CycleRateResultVoBuilder cycleRate(BigDecimal cycleRate) {
            this.cycleRate = cycleRate;
            return this;
        }

        public CycleRateResultVoBuilder cycleFirstDay(String cycleFirstDay) {
            this.cycleFirstDay = cycleFirstDay;
            return this;
        }

        public CycleRateResultVoBuilder cycleLastDay(String cycleLastDay) {
            this.cycleLastDay = cycleLastDay;
            return this;
        }

        public CycleRateResultVo build() {
            CycleRateResultVo cycleRateResultVo = new CycleRateResultVo();
            cycleRateResultVo.setCycleRate(cycleRate);
            cycleRateResultVo.setCycleFirstDay(cycleFirstDay);
            cycleRateResultVo.setCycleLastDay(cycleLastDay);
            return cycleRateResultVo;
        }
    }
}
