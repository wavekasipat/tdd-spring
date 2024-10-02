package com.example.day1.user;

public class UserResponse{
	private String fname;
	private String lname;
	private int id;

	public void setFname(String fname){
		this.fname = fname;
	}

	public String getFname(){
		return fname;
	}

	public void setLname(String lname){
		this.lname = lname;
	}

	public String getLname(){
		return lname;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"HelloResponse{" + 
			"fname = '" + fname + '\'' + 
			",lname = '" + lname + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}
