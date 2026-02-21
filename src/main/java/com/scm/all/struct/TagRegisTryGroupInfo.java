package com.scm.all.struct;

/**
 * 注册表信息
 */
public class TagRegisTryGroupInfo {
    public String Keys;
    public int types;
    public String typeName;
    public String values;

    @Override
    public String toString() {
        return "TagRegisTryGroupInfo{" +
                "Keys='" + Keys + '\'' +
                ", types=" + types +
                ", typeName='" + typeName + '\'' +
                ", values='" + values + '\'' +
                '}';
    }
}
