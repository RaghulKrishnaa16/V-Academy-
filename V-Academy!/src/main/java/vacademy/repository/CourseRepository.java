/**
 * 
 */
package vacademy.repository;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import vacademy.entity.Course;


@Transactional
public interface CourseRepository extends CrudRepository<Course, Integer> {
	@Query("select c from Course c where c.name = :name")
	Collection<Course> findByName(@Param("name") String name);
	
	@Query(value="DELETE c FROM Course AS c WHERE c.courseId = :courseId", nativeQuery=true)
	@Modifying
	void deleteCourseById(@Param("courseId") int courseId);
}
