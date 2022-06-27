package com.example.demo.jdbc;

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
@Table(name="stu")
public class StudentEntity {
	@Id
	@GeneratedValue
	private int id;
	private  String name;
	private int marks;
	private int percentage;
	private String Status;
	private String gender;
	@Transient
	private int reqPercentageToPass;
}
