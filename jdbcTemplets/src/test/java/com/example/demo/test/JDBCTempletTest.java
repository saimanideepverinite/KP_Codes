package com.example.demo.test;


import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.example.demo.jdbc.StudentEntity;

import io.restassured.response.ResponseBodyExtractionOptions;
import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.Response;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JDBCTempletTest {
	@LocalServerPort
	private int port;
	 @Test
	 void testSave() {	 
		 StudentEntity student1=dummyStudent();
		 String baseURI="http://localhost:"+port;
		 given()
				.contentType("application/json")
				.accept("application/json")
                .body(student1)
                .when()
                .post(baseURI+"/insert").then().statusCode(201)
                .contentType("application/json").extract().response();
		
//          String res =  response.jsonPath().getString("percentage");
//          assertNotNull(res);

	 }
	 @Test
	 void testGetAll() {
//		 String baseURI="http://localhost:"+port;
//		given().contentType("application/json").accept("application/json").when().get(baseURI+"/getAll").then().statusCode(200).contentType("application/json").extract().response();
//	 
	 }
	 public StudentEntity dummyStudent(){
	        StudentEntity student1 = new StudentEntity();
	        student1.setId(1);
	        student1.setName("sai");
	        student1.setMarks(85);
	        student1.setPercentage(85);
	        student1.setGender("male");
	        student1.setStatus("pass");
	        return  student1;
	    }
	
}
