package com.hyf.test.basic;

import java.util.stream.IntStream;

/**
 * @author baB_hyf
 * @date 2022/07/17
 */
public class Streams {

    public static void main(String[] args) {
        IntStream.iterate(0, i -> (i + 1) % 2).distinct().limit(3).forEach(System.out::println);
    }
}
