package cn.tellwhy.report.agent;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;


/*
Exception in thread "Attach Listener" java.lang.NoSuchMethodException: cn.tellwhy.report.agent.AgentTest.agentmain(java.lang.String, java.lang.instrument.Instrumentation)
        at java.lang.Class.getDeclaredMethod(Class.java:2130)
        at sun.instrument.InstrumentationImpl.loadClassAndStartAgent(InstrumentationImpl.java:327)
        at sun.instrument.InstrumentationImpl.loadClassAndCallAgentmain(InstrumentationImpl.java:411)
        Agent failed to start!
*/

/**
 * Created by aaa on 17-12-19.
 */
public class AgentTest {
    public static void premain(String agentArgs, Instrumentation inst){
        System.out.println(agentArgs);
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
