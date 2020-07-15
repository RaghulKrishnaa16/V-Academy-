package vacademy.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vacademy.entity.Administrator;

/**
 * @author dey
 *
 */
@Transactional
public interface AdministratorRepository<T> extends BasePersonRepository<Administrator> {
	@Query(value="SELECT a.*,p.* FROM Administrator a INNER JOIN Person p ON a.personId=p.personId WHERE p.username=:username AND p.password=:password", nativeQuery=true)
	public Administrator findByUsernameAndPassword(@Param("username")String username, @Param("password")String password);
}
