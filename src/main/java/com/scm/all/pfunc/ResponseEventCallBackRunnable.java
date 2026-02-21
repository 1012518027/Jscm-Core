package com.scm.all.pfunc;//package com.all.struct;


import com.scm.all.export.ModuleOperationUtilsJNI;
import com.scm.all.export.SystemUtils;

public class ResponseEventCallBackRunnable implements Runnable {
    public static int cycle;
    public int[] cacheKeys = new int[256];
    public int temp = 0;
    @Override
    public void run() {
        synchronized (this){
            while (true){
                try {
                    for(int j = 0;j<cacheKeys.length;j++){
                        cacheKeys[j] = 251;
                        cacheKeys[j] = ModuleOperationUtilsJNI.GetAsyncKeyState(j);
                    }
                    for(int i = 0; i < SystemUtils.hotkeyinfotowList.size(); i++){
                        if(SystemUtils.hotkeyinfotowList.get(i).logo !=0){
                            temp = (byte)Math.abs(SystemUtils.hotkeyinfotowList.get(i).keyCode);
                            temp = cacheKeys[temp];
                            if(temp==0){
                                //无状态
                            }
                            if(temp==1){

                            }
                            if(temp == -32768){
                                SystemUtils.hotkeyinfotowList.get(i).response.callback();
                                Thread.sleep(cycle);
                            }
                        }
                    }
                }catch (Exception e){e.printStackTrace();}
            }
        }
    }
}
