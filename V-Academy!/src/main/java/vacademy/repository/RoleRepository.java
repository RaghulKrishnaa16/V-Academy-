
package vacademy.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import vacademy.entity.Role;


@Transactional
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
