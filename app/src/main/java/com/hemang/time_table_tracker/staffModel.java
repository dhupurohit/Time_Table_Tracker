package com.hemang.time_table_tracker;

//public class staffModel
//{
//    public static int id;
//    public static String name;
//
//    public staffModel(int id,String name)
//    {
//        this.id = id;
//        this.name = name;
//    }
//
//    public static int getId() { return id; }
//
//    public void setId(int id) { this.id = id; }
//
//
//    public static String getName() { return  name;}
//
//    public void setName(String name) { this.name = name; }
//
//    @Override
//    public String toString()
//    {
//        return "staffModel{" +
//                "id=" + id +
//                ", login='" + name + '\'' +
//
//                '}';
//    }
//}
public class staffModel {
    private int id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;

    public staffModel(int id, String username, String email, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "StaffModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
