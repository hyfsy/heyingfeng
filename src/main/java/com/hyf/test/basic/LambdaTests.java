package com.hyf.test.basic;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * -Djdk.internal.lambda.dumpProxyClasses=. -Djava.lang.invoke.MethodHandle.DUMP_CLASS_FILES=true
 *
 * @author baB_hyf
 * @date 2022/07/17
 */
public class LambdaTests {

    public static void main(String[] args) {
        Consumer<String> stringStringFunction = System.out::println;
        BinaryOperator<String> operator = (s1, s2) -> {
            System.out.println(s1);
            return s2;
        };
        LambdaTests lambdaTests = new LambdaTests();
        Supplier<String> supplier = lambdaTests::getStr;
        stringStringFunction.accept("x");
        operator.apply("1", "2");
        System.out.println(supplier.get());
    }

    public String getStr() {
        return "str";
    }
}
