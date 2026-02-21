package com.scm.all.pfunc;//package com.all.struct;


import com.scm.all.export.ModuleOperationUtilsJNI;
import com.scm.all.export.SystemUtils;

public class ResponseEventCallTowBackRunnable implements Runnable {
    public static int cycle;
    public int[] cacheKeys = new int[256];
    public int functionState = 0;
    public int[] temp = new int[256];
    public int keyTemp = 0;
    @Override
    public void run() {
        synchronized (this){
            while (true){
                try {
                    for(int j = 0;j<cacheKeys.length;j++){
                        cacheKeys[j] = 251;
                        cacheKeys[j] = ModuleOperationUtilsJNI.GetAsyncKeyState(j);
                    }
                    functionState = (ModuleOperationUtilsJNI.GetAsyncKeyState(18) == -32768 ? 1:0) + (ModuleOperationUtilsJNI.GetAsyncKeyState(17) == -32768 ? 2:0) + (ModuleOperationUtilsJNI.GetAsyncKeyState(16) == -32768 ? 4:0) + (ModuleOperationUtilsJNI.GetAsyncKeyState(91) == -32768 ? 8:0);
                    cacheKeys[18] = 0;
                    cacheKeys[17] = 0;
                    cacheKeys[16] = 0;
                    cacheKeys[91] = 0;
                    cacheKeys[160] = 0;
                    cacheKeys[161] = 0;
                    cacheKeys[162] = 0;
                    cacheKeys[163] = 0;
                    cacheKeys[164] = 0;
                    cacheKeys[165] = 0;
                    for(int i = 0; i < SystemUtils.hotkeyinfothreeList.size(); i++){
                        if(SystemUtils.hotkeyinfothreeList.get(i).logo != 0){
                            if(functionState == SystemUtils.hotkeyinfothreeList.get(i).functionKey){
                                if(SystemUtils.hotkeyinfothreeList.get(i).keyState){
                                    keyTemp = (byte)Math.abs(SystemUtils.hotkeyinfothreeList.get(i).keyCode);
                                    keyTemp = cacheKeys[keyTemp];
                                    if(keyTemp==0){
                                        //无状态
                                    }
                                    if(keyTemp==1){
                                        temp[SystemUtils.hotkeyinfothreeList.get(i).keyCode] = 1;
                                    }
                                    if(keyTemp == -32768){
                                        temp[SystemUtils.hotkeyinfothreeList.get(i).keyCode] = 1;
                                        SystemUtils.hotkeyinfothreeList.get(i).response.callback();
                                        Thread.sleep(cycle);
                                    }
                                    if(temp == cacheKeys){
                                        SystemUtils.hotkeyinfothreeList.get(i).keyState = false;
                                        SystemUtils.hotkeyinfothreeList.set(i,SystemUtils.hotkeyinfothreeList.get(i));
                                    }

                                }else {
                                    SystemUtils.hotkeyinfothreeList.get(i).keyState =  temp == (temp=cacheKeys);
                                    SystemUtils.hotkeyinfothreeList.set(i,SystemUtils.hotkeyinfothreeList.get(i));
                                }
                            }else {
                                SystemUtils.hotkeyinfothreeList.get(i).keyState = false;
                                SystemUtils.hotkeyinfothreeList.set(i,SystemUtils.hotkeyinfothreeList.get(i));
                            }
                        }
                    }
                }catch (Exception e){e.printStackTrace();}
            }
        }
    }
}
