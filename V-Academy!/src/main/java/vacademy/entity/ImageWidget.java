package vacademy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "ImageWidget")
@PrimaryKeyJoinColumn(referencedColumnName = "widgetId")
public class ImageWidget extends Widget {

	private static final long serialVersionUID = 1923699140530704516L;

	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "imageId", updatable = false, nullable = false)
	private int imageId;

	@Column(name = "url")
	private String url;

	@Column(name = "verticalAlign")
	private String verticalAlign;

	@Column(name = "horizontalAlign")
	private String horizontalAlign;

	@JsonIgnore
	@Transient
	private final String widgetType = "imagewidget";
	
	public int getImageId() {
		return imageId;
	}

	
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	
	public String getUrl() {
		return url;
	}

	
	
	public void setUrl(String url) {
		this.url = url;
	}

	
	public String getVerticalAlign() {
		return verticalAlign;
	}

	
	public void setVerticalAlign(String verticalAlign) {
		this.verticalAlign = verticalAlign;
	}

	
	public String getHorizontalAlign() {
		return horizontalAlign;
	}

	
	public void setHorizontalAlign(String horizontalAlign) {
		this.horizontalAlign = horizontalAlign;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@JsonProperty
	public String getWidgetType() {
		return widgetType;
	}

}
