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
     * Enter zhe Method
     */
    @Override
    protected void onMethodEnter() {
        if ("onCreate".equals(name)) {
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESTATIC, "com/update/helper/TraceHelper", "onActivityCreate", "(Landroid/app/Activity;)V", false);
        } else if ("onDestroy".equals(name)) {
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESTATIC, "com/update/helper/TraceHelper", "onActivityDestroy", "(Landroid/app/Activity;)V", false);
        }
    }

    /**
     * end of Method
     */
    @Override
    protected void onMethodExit(int i) {
        super.onMethodExit(i);
    }
}