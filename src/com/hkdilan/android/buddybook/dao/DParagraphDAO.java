package com.hkdilan.android.buddybook.dao;

import java.util.Calendar;

public class DParagraphDAO {

	private String _id;
	private String content;
	private Calendar date;
	private Calendar dateTimeLastUpdate;
	private Calendar dateTimeSubmit;
	private String image;
	private int status;
	
	public String getId(){
		return this._id;
	}
	public void setId(String id){
		this._id = id;
	}
	
	public String getContent(){
		return this.content;
	}
	public void setContent(String content){
		this.content = content;
	}

	public Calendar getDate(){
		return this.date;
	}
	public void setDate(Calendar date){
		this.date = date;
	}
	
	public Calendar getDateTimeLastUpdate(){
		return this.dateTimeLastUpdate;
	}
	public void setDateTimeLastUpdate(Calendar dateTimeLastUpdate){
		this.dateTimeLastUpdate = dateTimeLastUpdate;
	}
	
	public Calendar getdateTimeSubmit(){
		return this.dateTimeSubmit;
	}
	public void setDateTimeSubmit(Calendar dateTimeSubmit){
		this.dateTimeSubmit = dateTimeSubmit;
	}
	
	public String getImage(){
		return this.image;
	}
	public void setImage(String image){
		this.image = image;
	}
	
	public int getStatus(){
		return this.status;
	}
	public void setStatus(int status){
		this.status = status;
	}
}
