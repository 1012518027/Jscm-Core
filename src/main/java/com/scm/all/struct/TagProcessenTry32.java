package com.scm.all.struct;

public class TagProcessenTry32{

    public int dwSize;
    public int cntUsage;
    public int th32ProcessID;
    public long th32DefaultHeapID;
    public int th32ModuleID;
    public int cntThreads;
    public int th32ParentProcessID;
    public int pcPriClassBase;
    public int dwFlags;
    public String szExeFile;

    @Override
    public String toString() {
        return "tagPROCESSENTRY32{" +
                "dwSize=" + dwSize +
                ", cntUsage=" + cntUsage +
                ", th32ProcessID=" + th32ProcessID +
                ", th32DefaultHeapID=" + th32DefaultHeapID +
                ", th32ModuleID=" + th32ModuleID +
                ", cntThreads=" + cntThreads +
                ", th32ParentProcessID=" + th32ParentProcessID +
                ", pcPriClassBase=" + pcPriClassBase +
                ", dwFlags=" + dwFlags +
                ", szExeFile=" + szExeFile +
                '}';
    }
}
