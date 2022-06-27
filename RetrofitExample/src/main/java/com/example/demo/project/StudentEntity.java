package com.example.demo.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;



public class StudentEntity {
		

	private int id;
	private  String name;
	private int marks;
	private int percentage;
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getReqPercentageToPass() {
		return reqPercentageToPass;
	}

	public void setReqPercentageToPass(int reqPercentageToPass) {
		this.reqPercentageToPass = reqPercentageToPass;
	}

	private String gender;

	private int reqPercentageToPass;
}
