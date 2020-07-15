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

import vacademy.entity.Administrator;
import vacademy.service.AdministratorService;


@Controller
public class AdminController {
	@Autowired
	private AdministratorService adminService;

	@GetMapping("api/administrator/{id}")
	public ResponseEntity<Administrator> getCourseById(@PathVariable("id") Integer id) {
		Administrator admin = adminService.getById(id);
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}
	
	@GetMapping("api/administrator/credentials")
	public ResponseEntity<Administrator> getStudentByCredentials(@RequestParam(value="username",required=true) String username, 
			@RequestParam(value="password",required=true) String password) {
		Administrator admin = adminService.findByUsernamePassword(username, password);
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}

	@GetMapping("api/administrator")
	public ResponseEntity<Collection<Administrator>> getAllCourses() {
		Collection<Administrator> admins = adminService.getAllPersons();
		return new ResponseEntity<>(admins, HttpStatus.OK);
	}
	
	@PostMapping("api/administrator")
	public ResponseEntity<Administrator> addStudent(@RequestBody Administrator admin) {
		Administrator newAdmin = adminService.add(admin);

		return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
	}

	@PutMapping("api/administrator")
	public ResponseEntity<Administrator> updateStudent(@RequestBody Administrator admin) {
		Administrator newAdmin = adminService.update(admin);

		return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
	}
	
	@PostMapping("api/administrator/delete")
	@DeleteMapping("api/administrator")
	public ResponseEntity<Administrator> deleteStudent(@RequestBody Administrator admin) {
		adminService.delete(admin);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
