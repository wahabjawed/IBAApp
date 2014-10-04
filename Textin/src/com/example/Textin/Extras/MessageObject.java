package com.example.Textin.Extras;

public class MessageObject {

	private String No;
	private String Msg;
	private String Name;

	public MessageObject(String no, String name, String msg) {

		this.No = no;
		this.Msg = msg;
		this.Name = name;

	}

	public String getNo() {
		return No;
	}

	public String getMsg() {
		return Msg;
	}


	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}

}
