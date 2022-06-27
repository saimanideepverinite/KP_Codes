package com.example.demo.jdbc;

import java.util.List;



public interface StudentService {
	public int saveStudent(StudentEntity entity);
	public List<StudentEntity> getGrater80();
	public List<StudentEntity> getGrater50();
	public List<StudentEntity> getGrater80Female();
	public List<StudentEntity> getGrater80Male();
	public List<StudentEntity> getAllStudents();
	public List<StudentEntity> getFailStudents();

}
