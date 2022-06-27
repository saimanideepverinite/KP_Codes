package com.example.demo.project;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.xml.transform.Result;

public class WireMockTest{
        @Rule
        public WireMockRule wireMockRule=new WireMockRule(8080);
        private  WireMockServer wireMockServer;

        @BeforeEach
        void setup(){
            wireMockServer = new WireMockServer();
            configureFor("localhost", 8083);
            wireMockServer.start();

        }


        @Test
        public void TestWireMockGetAll() throws IOException {
            stubFor(any((anyUrl())).willReturn(ok()));
            OkHttpClient client=new OkHttpClient().newBuilder().build();
            Request request= new Request.Builder().url("http://localhost:8083/getAll").method("GET",null).build();
            Response response=client.newCall(request).execute();
            System.out.println(response);
            assertNotNull(response);
        }
        
        @Test
        public void TestWireMockToSaveStudent() throws  IOException{
            stubFor(any((anyUrl())).willReturn(ok()));
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            Request request = new Request.Builder().url("http://localhost:8083/saveStudents").build();
            Response response = client.newCall(request).execute();
            System.out.println(response);
            assertNotNull(response);

        }
        @Test
        public void TestWireMockGetById() throws IOException {
            stubFor(any((anyUrl())).willReturn(ok()));
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            Request request = new Request.Builder().url("http://localhost:8083/id/1").method("GET", null).build();
            Response response = client.newCall(request).execute();
            System.out.println(response);
            assertNotNull(response);
        }

   


        @AfterEach
        void after(){
            wireMockServer.stop();
        }


    }