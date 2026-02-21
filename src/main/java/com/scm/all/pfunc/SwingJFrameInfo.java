package com.scm.all.pfunc;

import javax.swing.*;

public class SwingJFrameInfo {
    public int frameHwnd;
    public int x;
    public int y;
    public int width;
    public int height;
    public String title;
    public JFrame frame;

    public int getFrameHwnd() {
        return frameHwnd;
    }

    public void setFrameHwnd(int frameHwnd) {
        this.frameHwnd = frameHwnd;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
