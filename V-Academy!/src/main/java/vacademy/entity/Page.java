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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Page")
public class Page implements Serializable {

	private static final long serialVersionUID = -1504124147364585796L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pageId", updatable = false, nullable = false)
	private Integer pageId;

	@Column(name = "name")
	private String name;

	@Column(name = "tooltipDescription")
	private String tooltipDescription;

	@Column(name = "createDate", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();

	@Column(name = "updateDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	@Column(name = "verticalOrder")
	private Integer verticalOrder;
    
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "layoutId")
	private Layout layout;

	
	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "page", orphanRemoval = true)
	private List<Tab> tabs = new ArrayList<>();

	
	public Integer getPageId() {
		return pageId;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getTooltipDescription() {
		return tooltipDescription;
	}

	
	public void setTooltipDescription(String tooltipDescription) {
		this.tooltipDescription = tooltipDescription;
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

	
	 
	public Integer getVerticalOrder() {
		return verticalOrder;
	}

	
	public void setVerticalOrder(Integer verticalOrder) {
		this.verticalOrder = verticalOrder;
	}

	
	public List<Tab> getTabs() {
		return tabs;
	}

	
	public void setTabs(List<Tab> tabs) {
		this.tabs = tabs;
	}

	
	public void addTab(Tab tab) {
		if (!tabs.contains(tab)) {
			tabs.add(tab);
		}
	}

	
	public void removeTab(Tab tab) {
		if (tabs.contains(tab)) {
			tabs.remove(tab);
		}
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}