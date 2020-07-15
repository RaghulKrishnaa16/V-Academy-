
package vacademy.service;

import java.util.Collection;

import vacademy.entity.Course;
import vacademy.model.CourseRole;



public interface ICourseService {
	public Course getCourseById(int id);

	public Collection<Course> getAllCourses();

	public Course addCourse(Course c);

	public Course updateCourse(Course course);

	public void deleteCourse(Course course);
	
	public Collection<CourseRole> getCourseRoleByPersonId(int id);

	public Course addCourse(Course course, int personId, String role);
	
	public void deleteCourseById(int courseId);

	void assignCourse(int courseId, int personId, String personType);

	void dropCourse(int courseId, int personId, String personType);
}
