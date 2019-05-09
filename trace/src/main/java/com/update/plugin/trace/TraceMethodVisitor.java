package com.update.plugin.trace;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class TraceMethodVisitor extends AdviceAdapter {
    private String name;
        protected TraceMethodVisitor(int i, MethodVisitor methodVisitor, int access, String name, String desc) {
            super(i, methodVisitor, access, name, desc);
            this.name = name;
        }

        /**
         * 方法开始之前回调
         */
        @Override
        protected void onMethodEnter() {
            if ("onCreate".equals(name)) {
                mv.visitVarInsn(ALOAD, 0);
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn("Hello Update onCreate");
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            } else if ("onDestroy".equals(name)) {
                mv.visitVarInsn(ALOAD, 0);
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn("Hello Update onDestroy");
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            }
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