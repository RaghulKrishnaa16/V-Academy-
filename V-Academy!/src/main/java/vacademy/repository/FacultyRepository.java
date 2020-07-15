package vacademy.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vacademy.entity.Faculty;

/**
 * @author dey
 *
 */
@Transactional
public interface FacultyRepository<T> extends BasePersonRepository<Faculty> {
	@Query(value="SELECT f.*,p.* FROM Faculty f INNER JOIN Person p ON f.personId=p.personId WHERE p.username=:username AND p.password=:password", nativeQuery=true)
	public Faculty findByUsernameAndPassword(@Param("username")String username, @Param("password")String password);
}
