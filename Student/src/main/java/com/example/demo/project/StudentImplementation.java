package com.example.demo.project;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;






@Service
public class StudentImplementation implements StudentService{
	@Autowired
	StudentRepo studentRepo;
	

	@Override
	public StudentEntity saveStudent(StudentEntity studentEntity) {
//		if(studentEntity.getMarks()<36) {
//			studentEntity.setStatus("fail");
//		}
//		else {
//			studentEntity.setStatus("pass");
//		}
		return studentRepo.save(studentEntity);
	}
	
	@Override
	public List<StudentEntity> getGrater80() {
		List<StudentEntity> list1=studentRepo.findAll();
		List<StudentEntity> list2=list1.stream().filter(n->(n.getPercentage()>80)).collect(Collectors.toList());
		return list2;
	}

	@Override
	public List<StudentEntity> getGrater50() {
		List<StudentEntity> list1=studentRepo.findAll();
		List<StudentEntity> list2=list1.stream().filter(n->(n.getPercentage()>50)).collect(Collectors.toList());
		return list2;
	}

	@Override
	public List<StudentEntity> getGrater80Female() {
		
		List<StudentEntity> list1=studentRepo.findAll();
		List<StudentEntity> list2=list1.stream().filter(n->(n.getPercentage()>80 )).collect(Collectors.toList());
		List<StudentEntity> list3=list2.stream().filter(n->(n.getGender().equals("female"))).collect(Collectors.toList());
		return list3;
	}

	@Override
	public List<StudentEntity> getGrater80Male() {
		List<StudentEntity> list1=studentRepo.findAll();
		List<StudentEntity> list2=list1.stream().filter(n->(n.getPercentage()>80 && n.getGender().equals("male"))).collect(Collectors.toList());
		return list2;
	}

	@Override
	public List<StudentEntity> getAllStudents() {
		
		return studentRepo.findAll();
	}

	@Override
	public List<StudentEntity> getFailStudents() {
		List<StudentEntity> stu1=studentRepo.findAll();
		List<StudentEntity> stu2;
		ArrayList<StudentEntity> stu3=new ArrayList<>();
		stu2=stu1.stream().filter(x->(x.getStatus().equals("fail"))).collect(Collectors.toList());
		for(StudentEntity s:stu2) 
		{
			s.setReqPercentageToPass(36-s.getPercentage());
			stu3.add(s);
			
		}
		return stu3;
		
	}


	
	
	public Object saveBulk(List<StudentEntity> file) {
		System.out.println(file.size());
		int sizeOfList=file.size();
		if(sizeOfList>10) {
			System.out.println("inside if");
		 int limit=sizeOfList/3;
		 List<StudentEntity> list1=file.subList(0, limit);
		 List<StudentEntity> list2=file.subList(limit+1, limit+limit);
		 List<StudentEntity> list3=file.subList(limit+limit+1, file.size());
		 CompletableFuture.supplyAsync(()->		 
		 			studentRepo.saveAll(list1))	
		 			.thenRunAsync(()->studentRepo.saveAll(list2))
		 			.thenRunAsync(()->studentRepo.saveAll(list3));	  

		}
		else {
			 CompletableFuture.supplyAsync(()->		 
	 			studentRepo.saveAll(file))	;				
			}
		
		return "Success";
	}

	
	
	
	
public Object saveBulkExecutor(List<StudentEntity> file) {
		Executor executor=Executors.newFixedThreadPool(5);
		int sizeOfList=file.size();
		if(sizeOfList>10) {
		 int limit=sizeOfList/3;
		 List<StudentEntity> list1=file.subList(0, limit);
		 List<StudentEntity> list2=file.subList(limit+1, limit+limit);
		 List<StudentEntity> list3=file.subList(limit+limit+1, file.size());
		 CompletableFuture.supplyAsync(()->		 
		 			studentRepo.saveAll(list1))	
		 			.thenRunAsync(()->studentRepo.saveAll(list2))
		 			.thenRunAsync(()->studentRepo.saveAll(list3));	  

		}
		else {
			 CompletableFuture.supplyAsync(()->		 
	 			studentRepo.saveAll(file))	;				
			}
		
		return "Success";
	}

		

	
public List<StudentEntity> sipplyMethod(){
	CompletableFuture<List<StudentEntity>> completableFutureList=CompletableFuture.supplyAsync(()->{
		System.out.println(Thread.currentThread().getName());

		return studentRepo.findAll();
	});
	
	try {
		return completableFutureList.get();
	} catch (InterruptedException | ExecutionException e) {
		e.printStackTrace();
	}
	return null;
}


public List<StudentEntity> sipplyMethodExecutor(){
	Executor executor=Executors.newCachedThreadPool();
	
	CompletableFuture<List<StudentEntity>> completableFutureList=CompletableFuture.supplyAsync(()->{
		return studentRepo.findAll();
	},executor);
	
	try {
		System.out.println(Thread.currentThread().getName());

		return completableFutureList.get();
	} catch (InterruptedException | ExecutionException e) {
		e.printStackTrace();
	}
	return null;
}

public Object readFromFile(File file) {
	List<StudentEntity> listOfStudentEntities= new ArrayList<StudentEntity>();
	try {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			String[] s = null;
			while((line=reader.readLine())!=null) 
			{
				System.out.println(line);
				line=reader.readLine();
				s=line.split("\\s+");
				StudentEntity stu= new StudentEntity();
				stu.setName(s[0]);
				stu.setGender(s[1]);
				stu.setMarks(Integer.valueOf(s[2]));
				stu.setPercentage(Integer.valueOf(s[3]));
				stu.setStatus(s[4]);
				stu.setReqPercentageToPass(50-Integer.valueOf(s[2]));
				listOfStudentEntities.add(stu);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return saveBulk(listOfStudentEntities);
	
	
}
	
	public StudentEntity byId(int id) {		
		try {
			return studentRepo.findById(id).get();
		} catch (Exception e) {
			return new StudentEntity();
		}

	}
	public StudentEntity deleteById(int id) {
		StudentEntity s=studentRepo.getthroughNumber(id);
		if(s!=null) {
			studentRepo.deleteById(id);			
		}
		return s;	
	}
	public String updateById(int id,StudentEntity studentEntity) {
		StudentEntity s=studentRepo.getthroughNumber(id);
		s.setName(studentEntity.getName());
		s.setPercentage(studentEntity.getPercentage());
		s.setMarks(studentEntity.getMarks());
		s.setStatus(studentEntity.getStatus());
		studentRepo.save(s);
		return "success";
		
	}
	







}