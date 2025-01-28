package com.hyf.test.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * -Djdk.internal.lambda.dumpProxyClasses=. -Djava.lang.invoke.MethodHandle.DUMP_CLASS_FILES=true
 *
 * @author baB_hyf
 * @date 2022/07/17
 */
public class LambdaTests {

    private static String static_string;
    public String instance_string;

    public static void main(String[] args) {
        // 1
        Consumer<String> stringStringFunction = System.out::println;
        // 2
        Consumer<String> stringStringFunction2 = s -> {
            System.out.println(s);
        };
        String s = "1";
        // 3
        Supplier<String> supplier = () -> s;
        // 4
        Supplier<String> supplier2 = () -> {
            System.out.println("1");
            return s;
        };
        // 5
        BinaryOperator<String> operator = (s1, s2) -> {
            System.out.println(s1);
            return s2;
        };
        LambdaTests lambdaTests = new LambdaTests();
        // 6
        Supplier<String> supplier3 = lambdaTests::getStr;
        stringStringFunction.accept("x");
        operator.apply("1", "2");
        System.out.println(supplier3.get());

        List<String> collect = Arrays.asList("1").stream()
                // 7
                .filter(Objects::nonNull)
                // 8
                .map(String::valueOf)
                // 9 10 11 12
                .collect(Collectors.toList());
        System.out.println(collect);

        // 13
        Consumer<String> stringStringFunction7 = s1 -> {
            System.out.println(static_string);
        };
        // 14
        Supplier<String> supplier4 = () -> lambdaTests.instance_string;
        // 15
        Supplier<List> supplier5 = ArrayList::new;
        // 16
        Supplier<List<String>> supplier6 = () -> new ArrayList<>();

        A a = new B();
        // 17
        Consumer<String> stringConsumer = a::run;

    }

    public void invokeSpecial() {
        // 18
        Supplier<Object> supplier7 = super::hashCode;
    }

    public String getStr() {
        return "str";
    }

    public static interface A {
        String run(String s);
    }

    public static class B implements A {

        @Override
        public String run(String s) {
            return s;
        }
    }

    public static class C extends B {

    }
}
