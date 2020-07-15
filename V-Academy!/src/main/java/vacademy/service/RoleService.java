
package vacademy.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import vacademy.entity.Role;
import vacademy.repository.RoleRepository;


public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	
	public Collection<Role> findAllRoles() {
		return (Collection<Role>) roleRepository.findAll();
	}
}
