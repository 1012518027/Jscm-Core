package com.scm.all.struct;

/**
 * 内存信息数据展示
 */
public class TagMemoryDataX64 {

    public String IsStatic;
    /**
     * 内存地址 十进制
     */
    public long tenAddress;
    /**
     * 内存地址 十六进制
     */
    public String HexAddress;
    public long MemoryProtect;
    public long MemoryType;
    public String MemoryMessage;

    public TagMemoryDataX64() {
    }

    public String getIsStatic() {
        return IsStatic;
    }

    public void setIsStatic(String isStatic) {
        IsStatic = isStatic;
    }

    public TagMemoryDataX64(String isStatic, long tenAddress, String hexAddress, long memoryProtect, long memoryType, String memoryMessage) {
        IsStatic = isStatic;
        this.tenAddress = tenAddress;
        HexAddress = hexAddress;
        MemoryProtect = memoryProtect;
        MemoryType = memoryType;
        MemoryMessage = memoryMessage;
    }

    public long getTenAddress() {
        return tenAddress;
    }

    public void setTenAddress(long tenAddress) {
        this.tenAddress = tenAddress;
    }

    public String getHexAddress() {
        return HexAddress;
    }

    public void setHexAddress(String hexAddress) {
        HexAddress = hexAddress;
    }

    public long getMemoryProtect() {
        return MemoryProtect;
    }

    public void setMemoryProtect(long memoryProtect) {
        MemoryProtect = memoryProtect;
    }

    public long getMemoryType() {
        return MemoryType;
    }

    public void setMemoryType(long memoryType) {
        MemoryType = memoryType;
    }

    public String getMemoryMessage() {
        return MemoryMessage;
    }

    public void setMemoryMessage(String memoryMessage) {
        MemoryMessage = memoryMessage;
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
