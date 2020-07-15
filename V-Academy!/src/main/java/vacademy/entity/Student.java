
package vacademy.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "Student")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(referencedColumnName = "personId")
public class Student extends Person {

	private static final long serialVersionUID = 1L;

	@Generated(GenerationTime.INSERT)
	@Column(name = "studentId", insertable = false)
	private Integer studentId;

	@Column(name = "createDate", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();

	@JsonIgnore
	@Transient
	private final String roleType = "student";

	
	public Date getCreateDate() {
		return createDate;
	}

	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
	public Integer getStudentId() {
		return studentId;
	}

	
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	@JsonProperty
	public String getRoleType() {
		return roleType;
	}

}
