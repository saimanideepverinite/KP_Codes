package com.example.demo.jdbc;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository



public class StudentImplementation implements StudentService{
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int saveStudent(StudentEntity entity) {
		return jdbcTemplate.update("INSERT INTO stu  (id,name, marks, percentage,gender,status) VALUES(?,?,?,?,?,?)",
		        new Object[] { entity.getId(),entity.getName(),entity.getMarks(),entity.getPercentage(),entity.getGender(),entity.getStatus()});		  
	
	}

	
	public List<Map<String, Object>> getAll(){
		return jdbcTemplate.queryForList("select * from stu");
				}
	
	
	public List<StudentEntity> findByName(String name) {
	    String q = "SELECT * from stu WHERE name ILIKE '%" + name + "%'";
	    return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(StudentEntity.class));
	  }
	
	@Override
	public List<StudentEntity> getGrater80() {
		String q = "SELECT * from stu WHERE percentage>80";
	    return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(StudentEntity.class));
	
	}

	@Override
	public List<StudentEntity> getGrater50() {
		return null;
	}

	@Override
	public List<StudentEntity> getGrater80Female() {
		return null;
	}

	@Override
	public List<StudentEntity> getGrater80Male() {
		return null;
	}

	@Override
	public List<StudentEntity> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentEntity> getFailStudents() {
		return null;
	}
	
	
	
}
