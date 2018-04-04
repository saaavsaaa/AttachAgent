package cn.tellwhy.report.agent.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aaa on 17-12-26.
 */
public class JPSGet {
    private static String abstractOrderResult(String key, String pattern, String result){
        if (!result.contains(key)){
            return "";
        }
        Stack<String> ns = new Stack();
        Pattern p = Pattern.compile(pattern);
        Matcher er = p.matcher(result);
        while(er.find()) {
            ns.push(er.group());
        }
        return ns.pop();
    }
    
    public static String get() {
        try {
            return runProcess("jps", " AppApplication", "\\d+");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    private static String runProcess(String order, String key, String pattern) throws IOException {
        Runtime run = Runtime.getRuntime();
        Process process = run.exec(order);
        return checkResult(process, key, pattern);
    }
    
    private static String checkResult(Process process, String key, String pattern) throws IOException {
        String line;
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while ((line = in.readLine()) != null) {
            String tid = abstractOrderResult(key, pattern, line);
            if (tid == null || tid == ""){
                continue;
            }
            return tid;
        }
        return "";
    }
}
