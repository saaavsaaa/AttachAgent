package cn.tellwhy.report.agent;

import jdk.internal.org.objectweb.asm.ClassVisitor;

import static jdk.internal.org.objectweb.asm.Opcodes.ASM4;
import static jdk.internal.org.objectweb.asm.Opcodes.V1_7;

/**
 * Created by aaa on 18-4-4.
 */
public class ChangeVersionAdapter extends ClassVisitor {
    public ChangeVersionAdapter(ClassVisitor cv) {
        super(ASM4, cv);
    }
    
    @Override
    public void visit(int version, int access, String name,
                      String signature, String superName, String[] interfaces) {
        System.out.println(name);
        cv.visit(V1_7, access, name, signature, superName, interfaces); //major version
    }
}
