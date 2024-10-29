package com.hyf.test.basic;

import org.objectweb.asm.*;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author baB_hyf
 * @date 2024/10/29
 */
public class LambdaTestsParser {

    public static void main(String[] args) throws Exception {
        File file = new File("E:\\study\\code\\idea4\\test\\target\\classes\\com\\hyf\\test\\basic\\LambdaTests.class");
        try (FileInputStream fis = new FileInputStream(file)) {
            ClassReader classReader = new ClassReader(fis);
            classReader.accept(new ClassVisitor(Opcodes.ASM5) {
                @Override
                public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                    return new MethodVisitor(Opcodes.ASM5, super.visitMethod(access, name, desc, signature, exceptions)) {

                        @Override
                        public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
                            super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
                        }
                    };
                }
            }, ClassReader.EXPAND_FRAMES);
        }
    }
}
