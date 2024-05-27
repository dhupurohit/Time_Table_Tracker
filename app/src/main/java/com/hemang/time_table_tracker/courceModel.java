package com.hemang.time_table_tracker;

public class courceModel
{
    public String cource_name;
    public String department;
    public String semester;



    public courceModel(String cource_name,String department,String semester)
    {
        this.cource_name = cource_name;
        this.department = department;
        this.semester = semester;
    }


    public String getCource_name() { return cource_name; }
    public void setCource_name(String cource_name) { this.cource_name = cource_name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getSemester() { return semester; }

    public void setSemester(String semester) { this.semester = semester; }

    @Override
    public String toString()
    {
        return "courceModel{" +

                ", Cource : " + cource_name + '\'' +
                ", department='" + department + '\'' +
                ", semester='" + semester + '\'' +

                '}';
    }
}
