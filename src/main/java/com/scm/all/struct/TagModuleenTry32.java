package com.scm.all.struct;

public class TagModuleenTry32 {
    public int dwSize;
    public int th32ModuleID;
    public int th32ProcessID;
    public int GlblcntUsage;
    public int ProccntUsage;
    public long modBaseAddr;
    public long modBaseSize;
    public long hModule;
    public String szModule;
    public String szExePath;

    @Override
    public String toString() {
        return "tagMODULEENTRY32{" +
                "dwSize=" + dwSize +
                ", th32ModuleID=" + th32ModuleID +
                ", th32ProcessID=" + th32ProcessID +
                ", GlblcntUsage=" + GlblcntUsage +
                ", ProccntUsage=" + ProccntUsage +
                ", modBaseAddr=" + modBaseAddr +
                ", modBaseSize=" + modBaseSize +
                ", hModule=" + hModule +
                ", szModule=" + szModule +
                ", szExePath=" + szExePath +
                '}';
    }
}
