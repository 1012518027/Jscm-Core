package com.scm.all.struct;

public class TagThreadEntry32 {
    public int dwSize;
    public int cntUsage;
    public int th32ThreadID;
    public int th32OwnerProcessID;
    public long tpBasePri;
    public long tpDeltaPri;
    public int dwFlags;

    @Override
    public String toString() {
        return "tagTHREADENTRY32{" +
                "dwSize=" + dwSize +
                ", cntUsage=" + cntUsage +
                ", th32ThreadID=" + th32ThreadID +
                ", th32OwnerProcessID=" + th32OwnerProcessID +
                ", tpBasePri=" + tpBasePri +
                ", tpDeltaPri=" + tpDeltaPri +
                ", dwFlags=" + dwFlags +
                '}';
    }
}
