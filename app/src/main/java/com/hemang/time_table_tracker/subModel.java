package com.hemang.time_table_tracker;

public class subModel
{
    public String subName;

    public subModel(String subName)
    {
        this.subName = subName;
    }

    public String getSubName() { return subName; }

    public void setSubName(String subName) { this.subName = subName; }

    @Override
    public String toString()
    {
        return "subModel{" +

                ", Name : '" + subName + '\'' +

                '}';
    }
}
