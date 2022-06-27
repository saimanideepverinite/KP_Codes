package com.example.demo.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepo extends JpaRepository<StudentEntity, Number> {

	@Query(value="select * from mytable where id=?1",nativeQuery = true)
	StudentEntity getthroughNumber(int id);

	

}
