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
@Table(name = "GoogleDocWidget")
@PrimaryKeyJoinColumn(referencedColumnName = "widgetId")
public class GoogleDocWidget extends Widget {

	private static final long serialVersionUID = -3150515093588410649L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "googleDocId", updatable = false, nullable = false)
	private int googleDocId;

	@Column(name = "url")
	private String url;

	@Column(name = "editable")
	private Boolean editable;

	@Column(name = "type")
	private String type;
	
	@JsonIgnore
	@Transient
	private final String widgetType = "googledocwidget";
	
	public int getGoogleDocId() {
		return googleDocId;
	}

	
	public void setGoogleDocId(int googleDocId) {
		this.googleDocId = googleDocId;
	}

	
	public String getUrl() {
		return url;
	}

	
	public void setUrl(String url) {
		this.url = url;
	}

	
	public Boolean getEditable() {
		return editable;
	}

	
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public String getType() {
		return type;
	}

	
	public void setType(String type) {
		this.type = type;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	@JsonProperty
	public String getWidgetType() {
		return widgetType;
	}

}
