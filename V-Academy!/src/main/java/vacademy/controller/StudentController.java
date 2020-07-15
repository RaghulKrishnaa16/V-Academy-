package vacademy.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import vacademy.entity.Student;
import vacademy.service.StudentService;


@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;

	@GetMapping("api/student/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") Integer id) {
		Student student = studentService.getById(id);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@GetMapping("api/student/credentials")
	public ResponseEntity<Student> getStudentByCredentials(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) {
		Student student = studentService.findByUsernamePassword(username, password);
		System.out.println(student);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@GetMapping("api/student")
	public ResponseEntity<Collection<Student>> getAllCourses() {
		Collection<Student> students = studentService.getAllPersons();
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

	@PostMapping("api/student")
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		Student newStudent = studentService.add(student);

		return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
	}

	@PutMapping("api/student")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
		Student newStudent = studentService.update(student);

		return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
	}
	
	@PostMapping("api/student/delete")
	@DeleteMapping("api/student")
	public ResponseEntity<Student> deleteStudent(@RequestBody Student student) {
		studentService.delete(student);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
