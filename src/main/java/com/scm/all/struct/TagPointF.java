package com.scm.all.struct;

public class TagPointF {
    public float x;
    public float y;
    @Override
    public String toString() {
        return "tagPOINT{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public TagPointF() {
    }

    public TagPointF(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
