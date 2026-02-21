package com.scm.all.struct;

/**
 * 我的窗体信息类
 */
public class TagMyWindwInfo {
    /**
     * 线程ID
     */
    public int th32ThreadID;
    /**
     * 进程ID
     */
    public int th32ProcessID = 0;
    /**
     * 窗口句柄
     */
    public int tempHander;

    @Override
    public String toString() {
        return "tagMYWINDOWINFO{" +
                "th32ThreadID=" + th32ThreadID +
                ", th32ProcessID=" + th32ProcessID +
                ", tempHander=" + tempHander +
                '}';
    }
}
