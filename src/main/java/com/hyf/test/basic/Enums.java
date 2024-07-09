package com.hyf.test.basic;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;

/**
 * @author baB_hyf
 * @date 2022/07/17
 */
public class Enums {

    public static void main(String[] args) {
        T[] enumConstants = T.class.getEnumConstants();
        System.out.println(Arrays.toString(enumConstants));

        EnumSet<T> ts = EnumSet.allOf(T.class);
        boolean add = ts.add(T.A);
        System.out.println(add);
        for (T t : ts) {
            System.out.println(t);
        }

        // empty
        EnumMap<T, Object> tObjectEnumMap = new EnumMap<>(T.class);
        tObjectEnumMap.forEach((k, v) -> {
            System.out.println(k + " -> " + v);
        });
    }

    public enum T {
        A, B, C
    }
}
