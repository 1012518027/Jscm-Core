package com.scm.all.struct;

public class TagRectF {
    public float left;
    public float top;
    public float right;
    public float bottom;

    public TagRectF() {
    }

    public TagRectF(float left, float top, float right, float bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public String toString() {
        return "tagRECT{" +
                "left=" + left +
                ", top=" + top +
                ", right=" + right +
                ", bottom=" + bottom +
                '}';
    }
}
