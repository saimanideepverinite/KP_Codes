package com.example.demo.project;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {
	@GET("getAll")
	Call<List<StudentEntity>> getAll();
	@POST("saveStudent")
    Call<StudentEntity> createRepo(@Body StudentEntity studentEntity);
	@GET("id/{id}")
	Call<StudentEntity> throughId(@Path("id") int id);
	@DELETE("id/{id}")
	Call<StudentEntity> deleteById(@Path("id") int id);
	@PATCH("id/{id}")
	Call<String> update(@Path("id") int id,@Body StudentEntity studentEntity);
}
