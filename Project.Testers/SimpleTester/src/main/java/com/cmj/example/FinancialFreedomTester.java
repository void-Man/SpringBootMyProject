package com.cmj.example;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author mengjie_chen
 * @description date 2020/12/10
 */
public class FinancialFreedomTester {

    @Test
    public void freeTest() {
        BigDecimal initAmount = new BigDecimal("20000");
        BigDecimal everyAmount = new BigDecimal("50000");
        BigDecimal incrAmount = new BigDecimal("15000");
        BigDecimal totalAmount = initAmount;
        BigDecimal finalAmount = new BigDecimal("3000000");
        BigDecimal rate = new BigDecimal("0.20");

        int year = 0;
        while (true) {
            year++;
            if (totalAmount.compareTo(finalAmount) >= 0) {
                break;
            }
            totalAmount = totalAmount.add(everyAmount.add(incrAmount.multiply(new BigDecimal(year - 1)))).add(totalAmount.multiply(rate));
            System.out.println("year： " + year + "，totalAmount is：" + totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
        }
    }

    /**
     * 贬值测试
     *
     * @param
     * @return void
     * @author mengjie_chen
     * @date 2020/12/13
     */
    @Test
    public void depreciationTest() {
        BigDecimal rate = new BigDecimal("6.8").divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal initAmount = new BigDecimal("7500");

        int year = 7;
        for (int i = 0; i < year; i++) {
            initAmount = initAmount.subtract(initAmount.multiply(rate));
        }
        System.out.println(initAmount);
    }

}
