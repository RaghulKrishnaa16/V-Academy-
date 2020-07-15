
package vacademy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vacademy.entity.Faculty;
import vacademy.repository.BasePersonRepository;
import vacademy.repository.FacultyRepository;


@Service
public class FacultyService extends PersonService<Faculty> {
	@Autowired
	private FacultyRepository<Faculty> repository;

	@Override
	protected BasePersonRepository<Faculty> getRepository() {
		return repository;
	}
	
	public Faculty findByUsernamePassword(String username, String password) {
		return repository.findByUsernameAndPassword(username, password);
	}
}
