package com.example.demo.project;

import java.util.List;

public interface StudentService {
	public StudentEntity saveStudent(StudentEntity studentEntity);
	public List<StudentEntity> getGrater80();
	public List<StudentEntity> getGrater50();
	public List<StudentEntity> getGrater80Female();
	public List<StudentEntity> getGrater80Male();
	public List<StudentEntity> getAllStudents();
	public List<StudentEntity> getFailStudents();
	
	
	

}
