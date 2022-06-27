package com.example.demo;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.project.RetrofitInterface;
import com.example.demo.project.RetrofitService;
import com.example.demo.project.StudentEntity;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TestRetrofit {
	
	Retrofit retrofit;
	@InjectMocks
	RetrofitService retrofitService;
	@Mock
	RetrofitInterface retrofitInterface;
	

	//@SuppressWarnings("unchecked")
	@Test
	void testThroughId() throws IOException {
		StudentEntity studentEntity = dummyStudent();
        int id = 1;
       Response<StudentEntity> expectedResponse= Response.success(studentEntity);
       Call<StudentEntity> call=mock(Call.class);
       when(retrofitInterface.throughId(id)).thenReturn(call);
       when(call.execute()).thenReturn(expectedResponse);
       retrofitService.allById(id);
	
	}
	
	
	
	
	
	//@SuppressWarnings("unchecked")
	@Test
	void testAll() throws IOException {
		StudentEntity studentEntity=dummyStudent();
		StudentEntity studentEntity1=dummyStudent1();
		List<StudentEntity> list1=new ArrayList<>();
		list1.add(studentEntity);
		list1.add(studentEntity1);
		Response<List<StudentEntity>> expectedResponse=Response.success(list1);
		Call<List<StudentEntity>> call=mock(Call.class);
		when(retrofitInterface.getAll()).thenReturn(call);
		when(call.execute()).thenReturn(expectedResponse);
		retrofitService.allGet();
		
	
	}
	
	@Test
	public void testSave() throws IOException {
		StudentEntity studentEntity = dummyStudent();
       Response<StudentEntity> expectedResponse= Response.success(studentEntity);
       Call<StudentEntity> call=mock(Call.class);
       when(retrofitInterface.createRepo(studentEntity)).thenReturn(call);
       when(call.execute()).thenReturn(expectedResponse);
       retrofitService.createStudent(studentEntity);
	}
	
	@Test
	public void testDelete() throws IOException {
		StudentEntity studentEntity = dummyStudent();
        int id = 1;
       Response<StudentEntity> expectedResponse= Response.success(studentEntity);
       Call<StudentEntity> call=mock(Call.class);
       when(retrofitInterface.deleteById(id)).thenReturn(call);
       when(call.execute()).thenReturn(expectedResponse);
       retrofitService.deleteById(id);
	}
	@Test
	public void testUpdate() throws IOException {
		StudentEntity studentEntity = dummyStudent();
		StudentEntity studentEntity1=dummyStudent1();
        int id = 1;
       Response<String> expectedResponse= Response.success("success");
       Call<String> call=mock(Call.class);
       when(retrofitInterface.update(id,studentEntity1)).thenReturn(call);
       when(call.execute()).thenReturn(expectedResponse);
       retrofitService.updateById(id, studentEntity1);
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
	public StudentEntity dummyStudent1(){
        StudentEntity student1 = new StudentEntity();
        student1.setId(1);
        student1.setName("sai manideep");
        student1.setMarks(85);
        student1.setPercentage(85);
        student1.setGender("male");
        student1.setStatus("pass");
        return  student1;
    }

	
}
