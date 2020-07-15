
package vacademy.model;

import java.util.Collection;

import vacademy.entity.Course;
import vacademy.entity.Role;


public class CourseRole {
	private Course course;
	private Collection<Role> roles;
	private boolean isViewOnly;
	
	public CourseRole(Course course, Collection<Role> roles) {
		this.course = course;
		this.roles = roles;
		this.isViewOnly = roles.size() == 1 && "Viewer".equals(roles.iterator().next().getName());
	}

	
	public Course getCourse() {
		return course;
	}

	
	public void setCourse(Course course) {
		this.course = course;
	}

	
	public Collection<Role> getRoles() {
		return roles;
	}

	
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public boolean isViewOnly() {
		return isViewOnly;
	}

	
	public void setViewOnly(boolean isViewOnly) {
		this.isViewOnly = isViewOnly;
	}
}
