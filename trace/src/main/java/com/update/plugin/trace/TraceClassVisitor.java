package com.update.plugin.trace;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * @author : liupu
 * date   : 2019/5/9
 * desc   :
 */
public class TraceClassVisitor extends ClassVisitor {

    public TraceClassVisitor(String className, ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }

    /**
     * ASM进入到类的方法时进行回调
     */
    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature,
                                     String[] exceptions) {
        MethodVisitor methodVisitor = cv.visitMethod(access, name, desc, signature, exceptions);
        methodVisitor = new TraceMethodVisitor(Opcodes.ASM5, methodVisitor, access, name, desc);
        return methodVisitor;
    }

    /**
     * 当ASM进入类时回调
     */
    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
    }

    public static class TraceMethodVisitor extends AdviceAdapter {
        protected TraceMethodVisitor(int i, MethodVisitor methodVisitor, int i1, String s, String s1) {
            super(i, methodVisitor, i1, s, s1);
        }

        /**
         * 方法开始之前回调
         */
        @Override
        protected void onMethodEnter() {

        }

        /**
         * 方法结束时回调
         *
         * @param i
         */
        @Override
        protected void onMethodExit(int i) {
            super.onMethodExit(i);
        }
    }

}
