package com.example.demo.project;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;


@RestController
public class RetrofitController {
	@Autowired
	RetrofitService retrofitService;
	
	
	@GetMapping("/retrofit/all")
	public void getAllStudents(){
		try {
			RetrofitInterface interface1=RitrofitApiClient.getClient().create(RetrofitInterface.class);
			interface1.getAll().enqueue(new Callback<List<StudentEntity>>() {
				@Override
				public void onFailure(Call<List<StudentEntity>> call, Throwable t) {
					System.out.println(t.getMessage());
					
				}

				@Override
				public void onResponse(Call<List<StudentEntity>> call, Response<List<StudentEntity>> response) {
					try {
						if(response.isSuccessful()) {
							List<StudentEntity> students=response.body();
							for(StudentEntity s:students)
								System.out.println(s.getName());
						}
						
					} catch (Exception e) {
						System.err.println(e.getMessage());
						
					}
				}
			});
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
			
	}

	@PostMapping("/saveEntity")
	public StudentEntity save(@RequestBody StudentEntity studentEntity) throws IOException {
		return retrofitService.createStudent(studentEntity);
	}
	@GetMapping("/retrofit/getAll")
	public List<StudentEntity> getAll() throws IOException {
		return retrofitService.allGet();
	}
	@GetMapping("/retrofit/{id}")
	public StudentEntity studentDetails(@PathVariable int id) throws IOException {
		return retrofitService.allById(id);
	}
	@DeleteMapping("retrofit/{id}")
	public StudentEntity delete(@PathVariable int id) throws IOException {
		return retrofitService.deleteById(id);
	}
	@PatchMapping("retrofit/{id}")
	public String update(@PathVariable int id,@RequestBody StudentEntity studentEntity) throws IOException {
		return retrofitService.updateById(id, studentEntity);
	}
}

