package com.scm.all.struct;

public class TagTokenPrivileges  {
    public int PrivilegeCount;
    public int LowPart;
    public int HighPart;
    public int Attributes;

    @Override
    public String toString() {
        return "_TOKEN_PRIVILEGES{" +
                "PrivilegeCount=" + PrivilegeCount +
                ", LowPart=" + LowPart +
                ", HighPart=" + HighPart +
                ", Attributes=" + Attributes +
                '}';
    }
}
