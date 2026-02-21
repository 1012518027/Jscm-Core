package com.scm.all.struct;

public class TagWinInfo {
    public int hwnd;
    public int th32ProcessID;
    public int threadID;
    public String className;
    public String titleName;

    @Override
    public String toString() {
        return "tagWININFO{" +
                "hwnd=" + hwnd +
                ", th32ProcessID=" + th32ProcessID +
                ", threadID=" + threadID +
                ", className='" + className + '\'' +
                ", titleName='" + titleName + '\'' +
                '}';
    }
}
