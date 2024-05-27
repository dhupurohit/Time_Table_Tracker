package com.hemang.time_table_tracker;

public class studentModel
{

    public String Std_Name;
    public int Roll_no;

    public String Course;

    public studentModel(String Std_Name,int Roll_no,String Cource)
    {
        this.Std_Name = Std_Name;
        this.Roll_no = Roll_no;
        this.Course = Cource;

    }

    public String getStd_Name() { return Std_Name; }
    public void setStd_Name(String std_Name) { Std_Name = std_Name; }

    public int getRoll_no() { return Roll_no; }
    public void setRoll_no(int roll_no) { Roll_no = roll_no; }

    public String getCourse() { return Course; }
    public void setCourse(String course) { Course = course; }

    @Override
    public String toString()
    {
        return "staffModel{" +
                ", Name='" + Std_Name + '\'' +
                ", Roll No : "+Roll_no+ '\'' +
                ", Course : "+Course+ '\'' +
                '}';
    }
}
