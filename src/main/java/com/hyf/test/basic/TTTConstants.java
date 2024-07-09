package com.hyf.test.basic;

/**
 * @author baB_hyf
 * @date 2023/03/19
 */
public class TTTConstants {
    public static final String STRING = "STRING";
    public static final Object OBJECT = new Object();
    public static final AAAEnum ENUM   = AAAEnum.A;
    public static final int INTEGER   = 1111;

    static {
        System.out.println("init");
    }
}
