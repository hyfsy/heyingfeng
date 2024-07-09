package com.hyf.test.date;

import java.time.Instant;
import java.util.Date;

/**
 * @author baB_hyf
 * @date 2022/07/16
 */
public class InstantTests {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(new Date());
        Instant instant = Instant.now();
        System.out.println(instant);


    }
}
