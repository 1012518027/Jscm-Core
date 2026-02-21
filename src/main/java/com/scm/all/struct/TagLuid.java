package com.scm.all.struct;

public class TagLuid {
    public int LowPart;
    public int HighPart;
    @Override
    public String toString() {
        return "_LUID{" +
                "LowPart=" + LowPart +
                ", HighPart=" + HighPart +
                '}';
    }
}
