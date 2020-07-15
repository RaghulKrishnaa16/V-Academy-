
package vacademy.entity;

import java.beans.Transient;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name = "CourseRole")
@IdClass(CourseRolePK.class)
public class CourseRole implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	public CourseRolePK id;

	@Column(name = "role", nullable = false, insertable = false, updatable = false)
	protected int role;

	@Column(name = "courseId", nullable = false, insertable = false, updatable = false)
	protected int courseId;

	@Column(name = "personId", nullable = false, insertable = false, updatable = false)
	protected int personId;

	
	public int getRole() {
		return role;
	}

	
	public void setRole(int role) {
		this.role = role;
	}

	
	public int getCourseId() {
		return courseId;
	}

	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	
	public int getPersonId() {
		return personId;
	}

	
	public void setPersonId(int personId) {
		this.personId = personId;
	}

	
	@Transient
	public CourseRolePK getId() {
		return id;
	}

	
	@Transient
	public void setId(CourseRolePK id) {
		this.id = id;
	}

}
