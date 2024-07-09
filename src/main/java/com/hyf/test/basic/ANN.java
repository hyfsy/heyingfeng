package com.hyf.test.basic;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author baB_hyf
 * @date 2022/07/17
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ANN {

    String value() default "xxx";
}
