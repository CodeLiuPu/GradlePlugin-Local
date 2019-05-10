package com.update.plugin.trace;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author : liupu
 * date   : 2019/5/9
 * desc   :
 */
public class TraceClassVisitor extends ClassVisitor {

    private String className;
    private String superName;
    private String[] interfaces;

    public TraceClassVisitor(String className, ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }

    /**
     * 当ASM进入类时回调
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
     * ASM进入到类的方法时进行回调
     */
    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature,
                                     String[] exceptions) {
        MethodVisitor methodVisitor = cv.visitMethod(access, name, desc, signature, exceptions);

        if (shouldInject()) {
            return methodVisitor;
        } else {
            methodVisitor = new TraceMethodVisitor(Opcodes.ASM5, methodVisitor, access, name, desc);
            return methodVisitor;
        }
    }

    /**
     * 判断是否是需要注入代码的类
     */
    private boolean shouldInject() {
        //如果父类名是AppCompatActivity则拦截这个方法
        return superName.contains("AppCompatActivity");
    }

}
