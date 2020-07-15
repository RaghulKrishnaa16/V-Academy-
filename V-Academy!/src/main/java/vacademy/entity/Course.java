
package vacademy.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "Course")
public class Course implements Serializable {

	private static final long serialVersionUID = -2592937593227686781L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "courseId", updatable = false, nullable = false)
	private int courseId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "identifier", nullable = false)
	private String identifier;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "createDate", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();

	@Column(name = "updateDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	@Column(name = "displayGridOrder")
	private Integer displayGridOrder;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "course")
	private Layout layout;

	
	public int getCourseId() {
		return courseId;
	}

	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getIdentifier() {
		return identifier;
	}

	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	
	public String getDescription() {
		return description;
	}

	
	public void setDescription(String description) {
		this.description = description;
	}

	
	public Date getCreateDate() {
		return createDate;
	}

	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
	public Date getUpdateDate() {
		return updateDate;
	}

	
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	
	public Integer getDisplayGridOrder() {
		return displayGridOrder;
	}

	public void setDisplayGridOrder(Integer displayGridOrder) {
		this.displayGridOrder = displayGridOrder;
	}

	
	public Layout getLayout() {
		return layout;
	}

	
	public void setLayout(Layout layout) {
		this.layout = layout;
	}

}
