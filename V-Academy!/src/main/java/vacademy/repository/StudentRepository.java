package vacademy.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vacademy.entity.Student;


@Transactional
public interface StudentRepository<T> extends BasePersonRepository<Student> {
	@Query(value = "SELECT s.*,p.* FROM Student s INNER JOIN Person p ON s.personId=p.personId WHERE p.username=:username AND p.password=:password", nativeQuery = true)
	public Student findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
