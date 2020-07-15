
package vacademy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vacademy.entity.Student;
import vacademy.repository.BasePersonRepository;
import vacademy.repository.StudentRepository;


@Service
public class StudentService extends PersonService<Student> {
	@Autowired
	private StudentRepository<Student> repository;

	@Override
	protected BasePersonRepository<Student> getRepository() {
		return repository;
	}

	public Student findByUsernamePassword(String username, String password) {
		return repository.findByUsernameAndPassword(username, password);
	}

}
