package com.scm.all.struct;

public class TagIntTable {
    public String sectionName;
    public String ConfigurationItemName;
    public String dataValue;

    @Override
    public String toString() {
        return "[" + sectionName + ']'+'\n' +
                ConfigurationItemName + '=' +
                dataValue +'\n';
    }
}
