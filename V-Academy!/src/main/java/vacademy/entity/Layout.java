
package vacademy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import vacademy.controller.ThemeController;


@Entity
@Table(name = "Layout")
public class Layout implements Serializable {

	private static final long serialVersionUID = 3537712068761959795L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "layoutId", updatable = false, nullable = false)
	private Integer layoutId;

	@OneToOne
	@JoinColumn(name = "courseId")
	private Course course;

	
	public void setCourse(Course course) {
		this.course = course;
	}

	@ManyToOne
	@JoinColumn(name = "themeId")
	@JsonIgnore //Theme Stop theme propagation
	private Theme theme;

	
	public void setTheme(Theme theme) {
		if (theme == null)
			this.theme = new ThemeController().getDefaultTheme().getBody();
		else
			this.theme = theme;
	}

	@Column(name = "name")
	private String name;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "createDate", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();

	@Column(name = "updateDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	@Column(name = "customBackground")
	private String customBackground;

	@Column(name = "stylesheetLink")
	private String stylesheetLink;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "layout", orphanRemoval = true)
	@JsonIgnore //Stop nested objects to propagate to the server
	private List<Page> pages = new ArrayList<>();

	
	public Integer getLayoutId() {
		return layoutId;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getTitle() {
		return title;
	}

	
	public void setTitle(String title) {
		this.title = title;
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

	
	public String getCustomBackground() {
		return customBackground;
	}

	
	public void setCustomBackground(String customBackground) {
		this.customBackground = customBackground;
	}

	
	public String getStylesheetLink() {
		return stylesheetLink;
	}

	
	public void setStylesheetLink(String stylesheetLink) {
		this.stylesheetLink = stylesheetLink;
	}

	
	public List<Page> getPages() {
		return pages;
	}

	
	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
