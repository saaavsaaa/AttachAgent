package cn.tellwhy.report.agent;

import java.lang.instrument.Instrumentation;


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
    public static void agentmain(String args, Instrumentation inst)
    {
        System.out.println("start agent args=" + args);
//        Class<?>[] classes = inst.getAllLoadedClasses();
        Class<?>[] classes = inst.getInitiatedClasses(ClassLoader.getSystemClassLoader());
        for (Class<?> cls : classes)
        {
            System.out.println(cls.getName());
        }
        
        System.out.println("agent attached.");
    }
}
