
package vacademy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "Administrator")
@PrimaryKeyJoinColumn(referencedColumnName = "personId")
public class Administrator extends Person {

	
	private static final long serialVersionUID = 1L;

	@Generated(GenerationTime.INSERT)
	@Column(name = "adminId", insertable = false)
	private int adminId;
	
	@JsonIgnore
	@Transient
	private final String roleType = "administrator";
	
	public int getAdminId() {
		return adminId;
	}

	
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	@JsonProperty
	public String getRoleType() {
		return roleType;
	}
}
