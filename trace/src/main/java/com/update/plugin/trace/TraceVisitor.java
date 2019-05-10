package com.update.plugin.trace;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author : liupu
 * date   : 2019/5/9
 * desc   :
 */
public class TraceVisitor extends ClassVisitor {

    private String className;
    private String superName;
    private String[] interfaces;

    public TraceVisitor(String className, ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }

    /**
     * ASM income Class
     */
    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        // 记录当前类的相关信息
        this.className = name;
        this.superName = superName;
        this.interfaces = interfaces;
    }

    /**
     * ASM income Method
     */
    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature,
                                     String[] exceptions) {
        MethodVisitor methodVisitor = cv.visitMethod(access, name, desc, signature, exceptions);

        if (shouldInject()) {
            methodVisitor = new TraceMethodVisitor(Opcodes.ASM5, methodVisitor, access, name, desc);
            return methodVisitor;
        } else {
            return methodVisitor;
        }
    }

    private boolean shouldInject() {
        return superName.contains("BaseActivity");
    }

}
