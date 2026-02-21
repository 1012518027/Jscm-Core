package com.scm.all.struct;

public class TagMonthCalItem {
    /**
     * 别名：日期
     * 日期
     */
    private int nDay;

    /**
     * 别名：类型
     * 1上月,2当月,3下月
     */
    private int nType;

    /**
     * 别名：状态
     * 组合状态 monthCal_state_flag_
     */
    private int nState;

    /**
     * 别名：项坐标
     * 项坐标（Java中使用Rectangle替代C++的RECT）
     */
    private TagRect rcItem;

    // 构造方法
    public TagMonthCalItem() {}

    public TagMonthCalItem(int nDay, int nType, int nState, TagRect rcItem) {
        this.nDay = nDay;
        this.nType = nType;
        this.nState = nState;
        this.rcItem = rcItem;
    }

    // Getter 和 Setter 方法
    public int getnDay() {
        return nDay;
    }

    public void setnDay(int nDay) {
        this.nDay = nDay;
    }

    public int getnType() {
        return nType;
    }

    public void setnType(int nType) {
        this.nType = nType;
    }

    public int getnState() {
        return nState;
    }

    public void setnState(int nState) {
        this.nState = nState;
    }

    public TagRect getRcItem() {
        return rcItem;
    }

    public void setRcItem(TagRect rcItem) {
        this.rcItem = rcItem;
    }
}
