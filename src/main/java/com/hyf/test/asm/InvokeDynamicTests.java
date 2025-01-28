package com.hyf.test.asm;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;

import java.io.FileOutputStream;
import java.lang.reflect.Method;

import static org.objectweb.asm.Opcodes.*;

/**
 * @author baB_hyf
 * @date 2024/10/31
 */
public class InvokeDynamicTests {

    public static void main(String[] args) throws Exception {
        Class<?> aClass = InvokeDynamicTests.class.getClassLoader().loadClass("hyf.test.SampleClass");
        Object o = aClass.newInstance();
        System.out.println();
        Method print = o.getClass().getMethod("print");
        System.out.println();
        print.invoke(o);
        System.out.println();
    }

    public static void maind(String[] args) throws Exception {
        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        // 省略了类的创建和字段的定义代码

        cw.visit(V1_8, ACC_PUBLIC, "hyf/test/SampleClass", "hyf/test/SampleClass", "java/lang/Object", new String[0]);
        cw.visitField(ACC_PUBLIC, "fieldName", "Ljava/lang/String;", "Ljava/lang/String;", "sss");

        mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        // 创建一个名为"print"的方法
        mv = cw.visitMethod(ACC_PUBLIC, "print", "()V", null, null);
        mv.visitCode();

        // 使用invokedynamic指令来访问字段并赋值
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitTypeInsn(NEW, "Lhyf/test/SampleClass;");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL, "Lhyf/test/SampleClass;", "<init>", "()V", false);
        mv.visitInvokeDynamicInsn("field", "(Lhyf/test/SampleClass;)Ljava/lang/String;", new Handle(H_PUTFIELD, "Lhyf/test/SampleClass;", "fieldName", "Ljava/lang/String;"), "Lhyf/test/SampleClass;");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

        mv.visitInsn(RETURN);
        mv.visitMaxs(3, 3);
        mv.visitEnd();

        // 输出修改后的类
        byte[] classBytes = cw.toByteArray();
        // 可以选择将classBytes写入文件系统或者直接定义为类加载
        new FileOutputStream("E:\\SampleClass.class").write(classBytes);
    }
}
