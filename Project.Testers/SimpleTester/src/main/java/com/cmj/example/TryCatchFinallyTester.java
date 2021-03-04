package com.cmj.example;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/2/24
 */
public class TryCatchFinallyTester {

    public static void main(String[] args) {
        int i;
        try {
            i = 1;
            System.out.println(i);
            throw new RuntimeException("exception");
        } catch (Exception e) {
            i = 2;
            System.out.println("catch i = " + i);
        } finally {
            i = 3;
            System.out.println("finally i = " + i);
        }
    }

}
