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
        BigDecimal incrAmount = new BigDecimal("10000");
        BigDecimal totalAmount = initAmount;
        BigDecimal finalAmount = new BigDecimal("3000000");
        BigDecimal rate = new BigDecimal("0.15");

        int year = 0;
        while (true) {
            year++;
            if (totalAmount.compareTo(finalAmount) >= 0) {
                break;
            }
            totalAmount = totalAmount.add(everyAmount.add(incrAmount.multiply(new BigDecimal(year - 1)))).add(totalAmount.multiply(rate));
            System.out.println("year： " + year + "，totalAmount is：" + totalAmount);
        }
    }

}
