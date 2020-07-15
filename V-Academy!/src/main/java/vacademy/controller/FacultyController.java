
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

import vacademy.entity.Faculty;
import vacademy.entity.Student;
import vacademy.service.FacultyService;


@Controller
public class FacultyController {
	@Autowired
	private FacultyService facultyService;

	@GetMapping("api/faculty/{id}")
	public ResponseEntity<Faculty> getCourseById(@PathVariable("id") Integer id) {
		Faculty faculty = facultyService.getById(id);
		return new ResponseEntity<>(faculty, HttpStatus.OK);
	}
	
	@GetMapping("api/faculty/credentials")
	public ResponseEntity<Faculty> getStudentByCredentials(@RequestParam(value="username",required=true) String username, 
			@RequestParam(value="password",required=true) String password) {
		Faculty faculty = facultyService.findByUsernamePassword(username, password);
		return new ResponseEntity<>(faculty, HttpStatus.OK);
	}

	@GetMapping("api/faculty")
	public ResponseEntity<Collection<Faculty>> getAllCourses() {
		Collection<Faculty> faculties = facultyService.getAllPersons();
		return new ResponseEntity<>(faculties, HttpStatus.OK);
	}

	@PostMapping("api/faculty")
	public ResponseEntity<Faculty> addStudent(@RequestBody Faculty faculty) {
		Faculty newFaculty = facultyService.add(faculty);

		return new ResponseEntity<>(newFaculty, HttpStatus.CREATED);
	}

	@PutMapping("api/faculty")
	public ResponseEntity<Faculty> updateStudent(@RequestBody Faculty faculty) {
		Faculty newFaculty = facultyService.update(faculty);

		return new ResponseEntity<>(newFaculty, HttpStatus.CREATED);
	}

	@PostMapping("api/faculty/delete")
	@DeleteMapping("api/faculty")
	public ResponseEntity<Student> deleteStudent(@RequestBody Faculty faculty) {
		facultyService.delete(faculty);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
