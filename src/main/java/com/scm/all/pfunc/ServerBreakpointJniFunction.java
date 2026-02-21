package com.scm.all.pfunc;

import com.scm.all.export.ModuleOperationUtilsJNI;

import java.util.HashMap;

/**
 * 这是线程类
 */
public class ServerBreakpointJniFunction {
    private static int ThreadID = -1;
    private static int ProcessId = -1;


    private static int getThreadID(int id) {
        ThreadID = -1;
        ProcessId = id;
        ModuleOperationUtilsJNI.GetThreadID(new TagThreadInfo(){
            @Override
            public void callBack(int dwSize, int cntUsage, int th32ThreadID, int th32OwnerProcessID, long tpBasePri, long tpDeltaPri, int dwFlags) {
                if (ProcessId==th32OwnerProcessID){
                    ThreadID = th32ThreadID;
                    System.out.println("线程ID：" + th32OwnerProcessID);
                    return;
                }
            }
        });
        return ThreadID;
    }
}
