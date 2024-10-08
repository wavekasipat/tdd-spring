package com.example.day1.user;

public class UserResponse {

  private String fname;
  private String lname;
  private int id;
  private int age;

  public void setFname(String fname) {
    this.fname = fname;
  }

  public String getFname() {
    return fname;
  }

  public void setLname(String lname) {
    this.lname = lname;
  }

  public String getLname() {
    return lname;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getAge() {
    return age;
  }

  @Override
  public String toString() {
    return (
      "UserResponse{" +
      "fname='" +
      fname +
      '\'' +
      ", lname='" +
      lname +
      '\'' +
      ", id=" +
      id +
      ", age=" +
      age +
      '}'
    );
  }
}
