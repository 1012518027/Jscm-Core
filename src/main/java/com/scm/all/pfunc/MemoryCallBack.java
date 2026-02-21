package com.scm.all.pfunc;

import com.scm.all.struct.TagPmemory_Basic_InfoRmationX64;
import com.scm.all.struct.TagPmemory_Basic_InfoRmationX86;

public class MemoryCallBack {
    public boolean data(TagPmemory_Basic_InfoRmationX86 tagPmemory_basic_infoRmationX86, String IsStatic, int tenAddress, String hexAddress, int memoryProtect, int memoryType){return false;}
    public boolean data(TagPmemory_Basic_InfoRmationX64 tagPmemory_basic_infoRmationX64, String IsStatic, long tenAddress, String hexAddress, int memoryProtect, int memoryType){return false;}
    public boolean data(TagPmemory_Basic_InfoRmationX86 tagPmemory_basic_infoRmationX86,byte[] data, String IsStatic, int tenAddress, String hexAddress, int memoryProtect, int memoryType){return false;};
    public boolean data(TagPmemory_Basic_InfoRmationX64 tagPmemory_basic_infoRmationX64,byte[] data, String IsStatic, long tenAddress, String hexAddress, int memoryProtect, int memoryType){return false;};

}
