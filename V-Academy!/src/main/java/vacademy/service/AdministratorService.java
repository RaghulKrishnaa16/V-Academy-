
package vacademy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vacademy.entity.Administrator;
import vacademy.repository.AdministratorRepository;
import vacademy.repository.BasePersonRepository;


@Service
public class AdministratorService extends PersonService<Administrator> {
	@Autowired
	private AdministratorRepository<Administrator> repository;

	@Override
	protected BasePersonRepository<Administrator> getRepository() {
		return repository;
	}
	
	public Administrator findByUsernamePassword(String username, String password) {
		return repository.findByUsernameAndPassword(username, password);
	}
}
