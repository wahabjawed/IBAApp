package com.example.Textin.Extras;

import android.graphics.Bitmap;

public class ContactObject {

	private String Name;
	private String No;
	private Bitmap DisplayPic;
	private String Status;
	private Boolean isOnTextin;

	public ContactObject(String name, String no, String status, int isOnTextin) {

		Name = name;
		No = no;
		Status = status;
		this.isOnTextin = (isOnTextin == 1) ? true : false;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getNo() {
		return No;
	}

	public void setNo(String no) {
		No = no;
	}

	public Bitmap getDisplayPic() {
		return DisplayPic;
	}

	public void setDisplayPic(Bitmap displayPic) {
		DisplayPic = displayPic;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Boolean getIsOnTextin() {
		return isOnTextin;
	}

	public void setIsOnTextin(Boolean isOnTextin) {
		this.isOnTextin = isOnTextin;
	}

}
