package com.example.day1.hello;

public class HelloResponse{
	private String message;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"HelloResponse{" + 
			"message = '" + message + '\'' + 
			"}";
		}
}
