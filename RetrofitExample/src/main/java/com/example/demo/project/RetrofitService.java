package com.example.demo.project;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class RetrofitService {
	RetrofitInterface retrofitInterface;
	static final String API_BASE_URL = "http://localhost:8083/";
    
    
	
    public RetrofitService() {
    	Gson gson = new GsonBuilder().setLenient()
    	        .create();
    	
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
       
    }
    
  
    
    public StudentEntity createStudent(StudentEntity studentEntity) throws  IOException{
    	Call<StudentEntity> retrofitCall = retrofitInterface.createRepo(studentEntity);

        Response<StudentEntity> response = retrofitCall.execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }

        return response.body();
    	
    }
    
    public List<StudentEntity> allGet() throws IOException {
    	Call<List<StudentEntity>> retrofitCall = retrofitInterface.getAll();

        Response<List<StudentEntity>> response = retrofitCall.execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }

        return response.body();
       
    	
    }
    public StudentEntity allById(int id) throws IOException {
    	Call<StudentEntity> retrofitCall = retrofitInterface.throughId(id);
    	Response <StudentEntity> response = retrofitCall.execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }

        return response.body();
    	
    }
    public StudentEntity deleteById(int id) throws IOException {
    	Call<StudentEntity> retrofitCall=retrofitInterface.deleteById(id);
    	Response< StudentEntity> response=retrofitCall.execute();
    	 if (!response.isSuccessful()) {
             throw new IOException(response.errorBody() != null
                     ? response.errorBody().string() : "Unknown error");
         }
    	
		return response.body();
    	
    }
    public String updateById(int id,StudentEntity studentEntity) throws IOException {
    	Call<String> retrofitCall=retrofitInterface.update(id,studentEntity);
    	Response<String> response=retrofitCall.execute();
    	
    	 if (!response.isSuccessful()) {
             throw new IOException(response.errorBody() != null
                     ? response.errorBody().string() : "Unknown error");
         }
    	
		return response.body();
 
    }
    }

   
  
    
