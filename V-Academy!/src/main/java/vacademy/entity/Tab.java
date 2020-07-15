package vacademy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Tab")
public class Tab implements Serializable {

	private static final long serialVersionUID = -7233601591525732841L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tabId", updatable = false, nullable = false)
	private Integer tabId;

	@Column(name = "name")
	private String name;

	@Column(name = "horizontalOrder")
	private Integer horizontalOrder;

	@ManyToOne
	@JoinColumn(name = "pageId")
	@JsonIgnore
	private Page page;

	
	public void setPage(Page page) {
		this.page = page;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "TabWidget", joinColumns = @JoinColumn(name = "tabId"), inverseJoinColumns = @JoinColumn(name = "widgetId"))
	@JsonIgnore
	private List<Widget> widgets = new ArrayList<>();

	
	public List<Widget> getWidgets() {
		return widgets;
	}

	
	public void setWidgets(List<Widget> widgets) {
		this.widgets = widgets;
	}
	
	public Integer getTabId() {
		return tabId;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public Integer getHorizontalOrder() {
		return horizontalOrder;
	}

	public void setHorizontalOrder(Integer horizontalOrder) {
		this.horizontalOrder = horizontalOrder;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
