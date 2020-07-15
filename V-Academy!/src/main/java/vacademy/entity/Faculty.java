

package vacademy.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "Faculty")
@PrimaryKeyJoinColumn(referencedColumnName = "personId")
public class Faculty extends Person {

	
	private static final long serialVersionUID = 1L;

	@Generated(GenerationTime.INSERT)
	@Column(name = "facultyId", insertable = false)
	private int facultyId;

	@Column(name = "createDate", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();
	
	@JsonIgnore
	@Transient
	private final String roleType = "faculty";
	
	
	public int getFacultyId() {
		return facultyId;
	}

	
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}


	@Override
	public Date getCreateDate() {
		return createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	@JsonProperty
	public String getRoleType() {
		return roleType;
	}

}
