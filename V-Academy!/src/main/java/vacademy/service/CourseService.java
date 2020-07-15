
package vacademy.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vacademy.entity.Course;
import vacademy.entity.Role;
import vacademy.model.CourseRole;
import vacademy.repository.CourseRepository;
import vacademy.repository.CourseRoleRepository;
import vacademy.repository.RoleRepository;


@Service
public class CourseService implements ICourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CourseRoleRepository courseRoleRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Course getCourseById(int id) {
		return courseRepository.findOne(id);
	}

	@Override
	public Collection<Course> getAllCourses() {
		final Collection<Course> courses = new ArrayList<>();
		courseRepository.findAll().iterator().forEachRemaining(courses::add);

		return courses;
	}

	@Override
	public Course addCourse(Course c) {
		return courseRepository.save(c);
	}

	@Override
	public Course updateCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public void deleteCourse(Course course) {
		courseRepository.delete(course);
	}
	
	public void deleteCourseById(int courseId) {
		courseRepository.deleteCourseById(courseId);
	}

	@Override
	public Collection<CourseRole> getCourseRoleByPersonId(int personId) {
		//return courseRoleRepository.findCoursesByPerson(id);
		final List<CourseRole> courseRoles = new ArrayList<>();
		
		for (Course course : courseRoleRepository.findCoursesByPerson(personId)) {
			courseRoles.add(new CourseRole(course, courseRoleRepository.findRolesByPersonAndCourse(personId, course.getCourseId())));
		}
		
		return courseRoles;
	}

	@Override
	@Transactional
	public Course addCourse(Course course, int personId, String personType) {
		Course newCourse = addCourse(course);
		final String role = role(personType);
		for (Role dbRole : roleRepository.findAll()) {
			if (dbRole.getName().equals(role)) {
				courseRoleRepository.insertCourseRole(personId, newCourse.getCourseId(), dbRole.getId());
				return newCourse;
			}
		}
		
		throw new IllegalStateException("No role found");
	}
	
	@Transactional
	@Override
	public void assignCourse(int courseId, int personId, String personType) {
		final String role = role(personType);
		for (Role dbRole : roleRepository.findAll()) {
			if (dbRole.getName().equals(role)) {
				courseRoleRepository.insertCourseRole(personId, courseId, dbRole.getId());
				return;
			}
		}
		
		throw new IllegalStateException("No role found");
	}
	
	@Transactional
	@Override
	public void dropCourse(int courseId, int personId, String personType) {
		final String role = role(personType);
		for (Role dbRole : roleRepository.findAll()) {
			if (dbRole.getName().equals(role)) {
				courseRoleRepository.deleteCourseForPerson(personId, courseId, dbRole.getId());
				return;
			}
		}
		
		throw new IllegalStateException("No role found");
	}
	
	
	private String role(String personType) {
		switch (personType) {
			case "student": return "Viewer";
			case "faculty": return "Owner";
			case "administrator": return "Administrator";
			default: throw new IllegalStateException("No mapping found for person:" + personType);
		}
		
		
	}
	

}
