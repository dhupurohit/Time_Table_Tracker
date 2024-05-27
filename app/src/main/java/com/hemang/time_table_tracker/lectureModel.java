package com.hemang.time_table_tracker;

public class lectureModel
{

    public String day;
    public int sub;

    public int time;

    public lectureModel(String day, int sub, int Cource)
    {
        this.day = day;
        this.sub = sub;
        this.time = Cource;

    }

    public String getDay() { return day; }
    public void setDay(String day) { this.day = day; }

    public int getSub() { return sub; }
    public void setSub(int sub) { this.sub = sub; }

    public int getTime() { return time; }
    public void setTime(int time) { this.time = time; }

    @Override
    public String toString()
    {
        return "lectureModel{" +
                ", Day='" + day + '\'' +
                ", Subject : "+ sub + '\'' +
                ", Time : "+ time + '\'' +
                '}';
    }
}
