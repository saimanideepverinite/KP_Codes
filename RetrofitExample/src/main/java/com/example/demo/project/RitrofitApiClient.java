package com.example.demo.project;




import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RitrofitApiClient{
	@Autowired
	RetrofitInterface retrofitInterface;
	private static Retrofit  retrofit=null;
	public static Retrofit getClient() {
		Gson gson=new GsonBuilder().setDateFormat("yyy-MM-dd").create();
		HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client1=new OkHttpClient.Builder().addInterceptor(interceptor).build();
		retrofit = new Retrofit.Builder().baseUrl("http://localhost:8083").
				addConverterFactory(GsonConverterFactory.create(gson)).client(client1).build();		
				return retrofit;	
	}

}
