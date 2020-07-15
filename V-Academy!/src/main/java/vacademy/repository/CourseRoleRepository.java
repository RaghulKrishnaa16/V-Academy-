/**
 * 
 */
package vacademy.repository;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import vacademy.entity.Course;
import vacademy.entity.Role;

/**
 * @author dey
 *
 */
@Transactional
public interface CourseRoleRepository extends CrudRepository<Course, Integer> {
	
	@Query(value="select r.* from Role r where r.id in "
			+ "(select c.role from CourseRole c where c.courseId=:courseId and c.personId=:personId)", nativeQuery=true)
	Collection<Object> findRolesByPersonAndCourseObject(@Param("personId") int personId, @Param("courseId") int courseId);
	
	default Collection<Role> findRolesByPersonAndCourse(int personId, int courseId) {
		Collection<Role> roles = new ArrayList<>();
		for (Object cr : findRolesByPersonAndCourseObject(personId, courseId)) {
			Object[] obj = (Object[])cr;
			roles.add(new Role((Integer) obj[0], (String) obj[1]));
		}
		
		return roles;
	}
	
	
	@Query(value="select c.* from Course c where c.courseId in "
			+ "(select cr.courseId from CourseRole cr where cr.personId=:personId) GROUP BY c.courseId", nativeQuery=true)
	Collection<Course> findCoursesByPerson(@Param("personId") int personId);
	
	@Query(value="INSERT INTO CourseRole(`personId`, `courseId`, `role`) VALUES(:personId, :courseId, :roleId)", nativeQuery=true)
	@Modifying
	void insertCourseRole(@Param("personId") int personId, @Param("courseId") int courseId, @Param("roleId") int roleId);
	
	
	
	@Query(value="DELETE c FROM CourseRole AS c WHERE c.courseId = :courseId AND c.personId=:personId AND c.role=:roleId", nativeQuery=true)
	@Modifying
	void deleteCourseForPerson(@Param("personId") int personId, @Param("courseId") int courseId, @Param("roleId") int roleId);
	
}
