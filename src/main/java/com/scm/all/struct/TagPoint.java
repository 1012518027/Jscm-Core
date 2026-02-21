package com.scm.all.struct;

public class TagPoint {
    public long x;
    public long y;
    @Override
    public String toString() {
        return "tagPOINT{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public TagPoint() {
    }

    public TagPoint(long x, long y) {
        this.x = x;
        this.y = y;
    }
}
