
package vacademy.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;


@Embeddable
public class CourseRolePK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// @Column(name = "role")
	protected int role;

	// @Column(name = "courseId")
	protected int courseId;

	// @Column(name = "personId")
	protected int personId;

	public CourseRolePK() {

	}

	public CourseRolePK(int role, int courseId, int personId) {
		this.role = role;
		this.courseId = courseId;
		this.personId = personId;
	}

}
