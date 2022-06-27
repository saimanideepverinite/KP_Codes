package com.example.demo.project;

import io.restassured.response.Response;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:TestApplication.properties")
class TestStudentController{
	
	@LocalServerPort
    private int port;
	
	 @Test
	 void testSave() {	 
		 
		 StudentEntity student1=dummyStudent();
		 String baseURI="http://localhost:"+port;
		Response responce = given()
				.contentType("application/json")
				.accept("application/json")
                .body(student1)
                .when()
                .post(baseURI+"/saveStudent").then().statusCode(200)
                .contentType("application/json").extract().response();
		
          String res = responce.jsonPath().getString("percentage");
          assertNotNull(res);

	 }
	 @Test
	 void testGetAll() {
		 String baseURI="http://localhost:"+port;
		given().contentType("application/json").accept("application/json").when().get(baseURI+"/getAll").then().statusCode(200).contentType("application/json").extract().response();
	 }
	 
	 @Test
	 void testUploadImage()  
	 {
		 File test= new File("C:\\Users\\sai\\Downloads\\studata.txt");
		 String baseURI="http://localhost:"+port;
		 given().
         multiPart("file",test).
         post(baseURI+"/uploadFile").then().statusCode(200)
         .extract().response();
		
		 }
	 
	 @Test
	 void testGrater80() {
		 String baseURI="http://localhost:"+port;
		 given().contentType("application/json").accept("application/json").when().get(baseURI+"/getThan80").then().statusCode(200).contentType("application/json").extract().response();
			
	 }
	 @Test
	 void testGrater50() {
		 String baseURI="http://localhost:"+port;
		 given().contentType("application/json").accept("application/json").when().get(baseURI+"/getThan50").then().statusCode(200).contentType("application/json").extract().response();
			
	 }
	 @Test
	 void testGrater80Females() {
		 String baseURI="http://localhost:"+port;
		 given().contentType("application/json").accept("application/json").when().get(baseURI+"/getThan80Female").then().statusCode(200).contentType("application/json").extract().response();
			
	 }
	 @Test
	 void testGrater80Males() {
		 String baseURI="http://localhost:"+port;
		 given().contentType("application/json").accept("application/json").when().get(baseURI+"/getThan80Male").then().statusCode(200).contentType("application/json").extract().response();
			
	 }
	 @Test
	 void testFailedStudents() {
		 String baseURI="http://localhost:"+port;
		 given().contentType("application/json").accept("application/json").when().get(baseURI+"/failedStudents").then().statusCode(200).contentType("application/json").extract().response();
	 }
	 
	 @Test
	 void testSaveAllBulk(){
		 List<StudentEntity> list=new ArrayList<StudentEntity>();
		 list.add(dummyStudent());
		 String baseURI="http://localhost:"+port;
		 given().body(list).contentType("application/json").accept("application/json").when().post(baseURI+"/saveBulk").then().statusCode(200).contentType("application/json").extract().response();	 
	 }
	 
	 
	 
	 @Test
	 void saveBulkExecutor() {
		 List<StudentEntity> list=new ArrayList<StudentEntity>();
		 for(int i=0;i<=11;i++)
			 list.add(dummyStudent());
		 String baseURI="http://localhost:"+port;
		 given().body(list).contentType("application/json").accept("application/json").when().post(baseURI+"/saveBulkExecutor").then().statusCode(200).contentType("application/json").extract().response();	 
	
		
	 }
	 @Test
	 void testGetThriughSupply() {
		 String baseURI="http://localhost:"+port;
		 given().contentType("application/json").accept("application/json").when().get(baseURI+"/supply").then().statusCode(200).contentType("application/json").extract().response();
	 
		 
	 }
	
	 @Test
	 void testGetThroughSupplyExecutor() {
		 String baseURI="http://localhost:"+port;
		 given().contentType("application/json").accept("application/json").when().get(baseURI+"/supplyExecutor").then().statusCode(200).contentType("application/json").extract().response();

			
		 
	 }
	 @Test
	 void saveBulkExecutors() {
		 List<StudentEntity> list=new ArrayList<StudentEntity>();
			 list.add(dummyStudent());
		 String baseURI="http://localhost:"+port;
		 given().body(list).contentType("application/json").accept("application/json").when().post(baseURI+"/saveBulkExecutor").then().statusCode(200).contentType("application/json").extract().response();	 
	
		
	 }
	 @Test
	 void testSave2() {
		 StudentEntity student1=dummyStudent1();
		 String baseURI="http://localhost:"+port;
		Response responce = given()
				.contentType("application/json")
				.accept("application/json")
                .body(student1)
                .when()
                .post(baseURI+"/saveStudent").then().statusCode(200)
                .contentType("application/json").extract().response();
		
          String res = responce.jsonPath().getString("percentage");
          assertNotNull(res);

	 }
	 
	 public StudentEntity dummyStudent(){
	        StudentEntity student1 = new StudentEntity();

	        student1.setName("sai");
	        student1.setMarks(85);
	        student1.setPercentage(85);
	        student1.setGender("male");
	        student1.setStatus("pass");
	        return  student1;
	    }
	 public StudentEntity dummyStudent1(){
	        StudentEntity student2 = new StudentEntity();

	        student2.setName("sai");
	        student2.setMarks(25);
	        student2.setPercentage(25);
	        student2.setGender("male");
	        student2.setStatus("fail");
	        return  student2;
	    }
	 }
	 


 

