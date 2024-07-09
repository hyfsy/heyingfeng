package com.hyf.test.basic;

/**
 * @author baB_hyf
 * @date 2022/07/17
 */
public class InvokeOrder {

    public static void main(String[] args) {
        new C();
    }

    public static class C extends P {

    }

    public static class P {

        public P() {
            System.out.println("P Const");
        }
        {
            System.out.println("P");
        }
    }
}
