package cn.tellwhy.report.agent;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.security.ProtectionDomain;


/*
Exception in thread "Attach Listener" java.lang.NoSuchMethodException: cn.tellwhy.report.agent.AgentTest.agentmain(java.lang.String, java.lang.instrument.Instrumentation)
        at java.lang.Class.getDeclaredMethod(Class.java:2130)
        at sun.instrument.InstrumentationImpl.loadClassAndStartAgent(InstrumentationImpl.java:327)
        at sun.instrument.InstrumentationImpl.loadClassAndCallAgentmain(InstrumentationImpl.java:411)
        Agent failed to start!
*/

/**
 * Created by aaa on 17-12-19.
 * META-INF/MANIFEST.MF build by build.gradle
 
     Manifest-Version: 1.0
     Agent-Class: cn.tellwhy.report.agent.AgentTest
     Premain-Class: cn.tellwhy.report.agent.AgentTest
     Boot-Class-Path: btrace-boot.jar
     Can-Redefine-Classes: true
     Can-Retransform-Classes: true
     Main-Class: cn.tellwhy.report.agent.AgentTest
 
 */
public class AgentTest {
//    public static void main(String[] args){}
        
    public static void premain(String agentArgs, Instrumentation inst){
        System.out.println(agentArgs);
    
        inst.addTransformer(new ClassFileTransformer() {
            public byte[] transform(ClassLoader l, String name, Class c,
                                    ProtectionDomain d, byte[] b)
                    throws IllegalClassFormatException {
                ClassReader cr = new ClassReader(b);
                ClassWriter cw = new ClassWriter(cr, 0);
                ClassVisitor cv = new ChangeVersionAdapter(cw);
                cr.accept(cv, 0);
                return cw.toByteArray();
            }
        });
    }
    
    public static void agentmain(String args, Instrumentation inst) throws NoSuchFieldException, IllegalAccessException {
        System.out.println("start agent args=" + args);
        Class<?>[] classes = inst.getAllLoadedClasses();
//        Class<?>[] classes = inst.getInitiatedClasses(ClassLoader.getSystemClassLoader());
//        inst.addTransformer();
        for (Class<?> one : classes) {
            if (one.getSimpleName().equals("RedisCacheProperties")) {
                System.out.println(one.getName());
//                System.out.println(one.toString());
//                System.out.println(one.getDeclaredField("cache"));
                Field attr = one.getDeclaredField("cache");
                System.out.println(attr.toString());
                System.out.println(attr.getGenericType());
                System.out.println(attr.toGenericString());
                System.out.println(attr.getType());
            }
        }
        
//        inst.addTransformer(new TestClassFileTransformer());
        
        System.out.println("agent attached.");
    }
}
