package com.example.demo.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mytable")
public class StudentEntity { 

	
//	public StudentEntity(String string, int i, int j, String string2, String string3) {
//		this.name=string;
//		this.marks=i;
//		this.percentage=j;
//		this.gender=string2;
//		this.status=string3;
//	}
	public StudentEntity() {
	}
	@Id
	@GeneratedValue
	private int id;
	private  String name;
	private int marks;
	private int percentage;
	private String status;
	private String gender;
	@Transient
	private int reqPercentageToPass;
	

	
}
