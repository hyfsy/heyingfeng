package com.hyf.test.basic;

// import com.hyf.hotrefresh.core.extend.ClassBytesDumper;
// import com.sun.corba.se.impl.io.ObjectStreamClass;

/**
 * @author baB_hyf
 * @date 2022/07/17
 */
@ANN("test")
public class ANNTests {
    public static void main(String[] args) throws Throwable {
        // AnnotationInvocationHandler
        ANN annotation = ANNTests.class.getAnnotation(ANN.class);
        System.out.println(annotation);
        System.out.println(annotation.getClass());

        // ClassBytesDumper.dump(annotation.getClass(), "E:\\A.class");
    }
}
