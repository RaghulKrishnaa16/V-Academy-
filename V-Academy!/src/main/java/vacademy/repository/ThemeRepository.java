package vacademy.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import vacademy.entity.Theme;


@Transactional
public interface ThemeRepository extends CrudRepository<Theme, Integer> {

	@Query(value = "SELECT * FROM Theme WHERE name=:name", nativeQuery = true)
	public Theme findByName(@Param("name") String name);
}
