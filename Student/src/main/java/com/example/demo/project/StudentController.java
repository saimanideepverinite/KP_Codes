package com.example.demo.project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class StudentController {
	@Autowired
	private StudentImplementation studentImplementation;

	
	@PostMapping("/saveStudents")
	public StudentEntity save(@RequestBody StudentEntity se) {
		return studentImplementation.saveStudent(se);
	}
	@GetMapping("/getThan80")
	public List<StudentEntity> getMore80(){
		return studentImplementation.getGrater80();
	}
	@GetMapping("/getThan50")
	public List<StudentEntity> getMore50(){
		return studentImplementation.getGrater50();
	}
	@GetMapping("/getThan80Female")
	public List<StudentEntity> getMore80Female(){
		return studentImplementation.getGrater80Female();
	}
	@GetMapping("/getThan80Male")
	public List<StudentEntity> getMore80Male(){
		return studentImplementation.getGrater80Male();
	}
	@GetMapping("/getAll")
	public List<StudentEntity> getAll(){
		System.out.println("In controller");
		return studentImplementation.getAllStudents();
	}
	@GetMapping("/failedStudents")
	public List<StudentEntity> getAllFailed(){
		return studentImplementation.getFailStudents();
	}
	@PostMapping("/saveBulk")
	public Object saveAll(@RequestBody List<StudentEntity> stu) {
		
		return studentImplementation.saveBulk(stu);		
		
	}
	@PostMapping("/saveBulkExecutor")
	public Object saveAllBulk(@RequestBody List<StudentEntity> stu) {
		return	studentImplementation.saveBulkExecutor(stu);			
	}
	@GetMapping("/supply")
	public List<StudentEntity> getThroughSupply(){
		return studentImplementation.sipplyMethod();
	}
	@GetMapping("/supplyExecutor")
	public List<StudentEntity> getThroughSupplyExecutor(){
		return studentImplementation.sipplyMethodExecutor();
	}
	@PostMapping("/uploadFile")
	public Object uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
		File f= new File("uploads/"+file.getOriginalFilename());
		OutputStream os = new FileOutputStream(f);
		os.write(file.getBytes());
		os.close();
		return studentImplementation.readFromFile(new File("uploads/"+file.getOriginalFilename()));
	}
	@GetMapping("/id/{no}")
	public Object getOneStudent(@PathVariable int no) {
		return studentImplementation.byId(no);
	}
	@DeleteMapping("/id/{no}")
	public StudentEntity delete(@PathVariable int no) {
		return studentImplementation.deleteById(no);
	}
	@PatchMapping("/id/{no}")
	public String update(@PathVariable int no,@RequestBody StudentEntity student) {
		return studentImplementation.updateById(no, student);
	}
	
	
}
