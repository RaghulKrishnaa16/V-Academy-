
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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Widget")
@Inheritance(strategy = InheritanceType.JOINED)
public class Widget implements Serializable {

	private static final long serialVersionUID = 2342067425654448001L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "widgetId", updatable = false, nullable = false)
	private Integer widgetId;

	@Column(name = "name")
	private String name;

	@Column(name = "topPosition")
	private Float topPosition;

	@Column(name = "bottomPosition")
	private Float bottomPosition;

	@Column(name = "leftPosition")
	private Float leftPosition;

	@Column(name = "rightPosition")
	private Float rightPosition;

	@Column(name = "width")
	private Float width;

	@Column(name = "height")
	private Float height;

	@Column(name = "foregroundColor")
	private String foregroundColor;

	@Column(name = "backgroundColor")
	private String backgroundColor;

	@Column(name = "cssClass")
	private String cssClass;

	@Column(name = "cssStyle")
	private String cssStyle;

	@Column(name = "scrollable")
	private Boolean scrollable;

	@Column(name = "fitContents")
	private Boolean fitContents;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "widgets")
	@JsonIgnore
	private List<Tab> tabs = new ArrayList<>();

	
	public void setTabs(List<Tab> tabs) {
		this.tabs = tabs;
	}

	
	public Integer getWidgetId() {
		return widgetId;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public Float getTopPosition() {
		return topPosition;
	}

	
	public void setTopPosition(Float topPosition) {
		this.topPosition = topPosition;
	}

	
	public Float getBottomPosition() {
		return bottomPosition;
	}

	
	public void setBottomPosition(Float bottomPosition) {
		this.bottomPosition = bottomPosition;
	}

	
	public Float getLeftPosition() {
		return leftPosition;
	}

	
	public void setLeftPosition(Float leftPosition) {
		this.leftPosition = leftPosition;
	}

	
	public Float getRightPosition() {
		return rightPosition;
	}

	
	public void setRightPosition(Float rightPosition) {
		this.rightPosition = rightPosition;
	}

	
	public Float getWidth() {
		return width;
	}

	
	public void setWidth(Float width) {
		this.width = width;
	}

	
	public Float getHeight() {
		return height;
	}

	
	public void setHeight(Float height) {
		this.height = height;
	}

	
	public String getForegroundColor() {
		return foregroundColor;
	}

	
	public void setForegroundColor(String foregroundColor) {
		this.foregroundColor = foregroundColor;
	}

	
	public String getBackgroundColor() {
		return backgroundColor;
	}

	
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	
	public String getCssClass() {
		return cssClass;
	}

	
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	
	public String getCssStyle() {
		return cssStyle;
	}

	
	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}

	
	public Boolean getScrollable() {
		return scrollable;
	}

	
	public void setScrollable(Boolean scrollable) {
		this.scrollable = scrollable;
	}

	
	public Boolean getFitContents() {
		return fitContents;
	}

	
	public void setFitContents(Boolean fitContents) {
		this.fitContents = fitContents;
	}

	
	public List<Tab> getTabs() {
		return tabs;
	}

}
