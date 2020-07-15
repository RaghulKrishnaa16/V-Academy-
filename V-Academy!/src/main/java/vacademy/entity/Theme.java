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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "Theme")
public class Theme implements Serializable {

	private static final long serialVersionUID = -4938018165702280836L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "themeId", updatable = false, nullable = false)
	private Integer themeId;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "version")
	private String version;

	@Column(name = "createDate", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();

	@Column(name = "updateDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	@Column(name = "noOfUses")
	private Integer noOfUses = 0;

	@Column(name = "themeBackgroundImage")
	private String themeBackgroundImage;

	@Column(name = "stylesheetLink")
	private String stylesheetLink;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "theme")
	private List<Layout> layouts = new ArrayList<>();

	
	public Integer getThemeId() {
		return themeId;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	
	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getVersion() {
		return version;
	}

	
	public void setVersion(String version) {
		this.version = version;
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

	
	public Integer getNoOfUses() {
		return noOfUses;
	}

	
	public void setNoOfUses(Integer noOfUses) {
		this.noOfUses = noOfUses;
	}

	
	public String getThemeBackgroundImage() {
		return themeBackgroundImage;
	}

	
	public void setThemeBackgroundImage(String themeBackgroundImage) {
		this.themeBackgroundImage = themeBackgroundImage;
	}

	
	public String getStylesheetLink() {
		return stylesheetLink;
	}

	
	public void setStylesheetLink(String stylesheetLink) {
		this.stylesheetLink = stylesheetLink;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
