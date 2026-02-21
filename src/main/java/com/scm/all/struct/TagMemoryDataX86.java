package com.scm.all.struct;

/**
 * 内存信息数据展示
 */
public class TagMemoryDataX86 {
    public String IsStatic;
    /**
     * 内存地址 十进制
     */
    public int tenAddress;
    /**
     * 内存地址 十六进制
     */
    public String HexAddress;
    public int MemoryProtect;
    public int MemoryType;
    public String MemoryMessage;

    public TagMemoryDataX86() {
    }

    public String getIsStatic() {
        return IsStatic;
    }

    public void setIsStatic(String isStatic) {
        IsStatic = isStatic;
    }

    public TagMemoryDataX86(String isStatic, int tenAddress, String hexAddress, int memoryProtect, int memoryType, String memoryMessage) {
        IsStatic = isStatic;
        this.tenAddress = tenAddress;
        HexAddress = hexAddress;
        MemoryProtect = memoryProtect;
        MemoryType = memoryType;
        MemoryMessage = memoryMessage;
    }

    public int getTenAddress() {
        return tenAddress;
    }

    public void setTenAddress(int tenAddress) {
        this.tenAddress = tenAddress;
    }

    public String getHexAddress() {
        return HexAddress;
    }

    public void setHexAddress(String hexAddress) {
        HexAddress = hexAddress;
    }

    public int getMemoryProtect() {
        return MemoryProtect;
    }

    public void setMemoryProtect(int memoryProtect) {
        MemoryProtect = memoryProtect;
    }

    public int getMemoryType() {
        return MemoryType;
    }

    public void setMemoryType(int memoryType) {
        MemoryType = memoryType;
    }

    public String getMemoryMessage() {
        return MemoryMessage;
    }

    public void setMemoryMessage(String memoryMessage) {
        MemoryMessage = memoryMessage;
    }

    @Override
    protected void finalize() throws Throwable {
        Thread.yield();
        super.finalize();
    }

    @Override
    public String toString() {
        return "TagMemoryDataX86{" +
                "IsStatic='" + IsStatic + '\'' +
                ", tenAddress=" + tenAddress +
                ", HexAddress='" + HexAddress + '\'' +
                ", MemoryProtect=" + MemoryProtect +
                ", MemoryType=" + MemoryType +
                ", MemoryMessage='" + MemoryMessage + '\'' +
                '}';
    }
}
