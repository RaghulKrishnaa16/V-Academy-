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
@Table(name = "VideoWidget")
@PrimaryKeyJoinColumn(referencedColumnName = "widgetId")
public class VideoWidget extends Widget {

	private static final long serialVersionUID = -4784006102250968762L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "videoId", updatable = false, nullable = false)
	private int videoId;

	@Column(name = "url")
	private String url;

	@Column(name = "youtubeId")
	private String youtubeId;

	@Column(name = "expandable")
	private Boolean expandable;
	
	@JsonIgnore
	@Transient
	private final String widgetType = "videowidget";
	
	
	public int getVideoId() {
		return videoId;
	}

	
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	
	public String getYoutubeId() {
		return youtubeId;
	}

	
	public void setYoutubeId(String youtubeId) {
		this.youtubeId = youtubeId;
	}

	
	public Boolean getExpandable() {
		return expandable;
	}

	
	public void setExpandable(Boolean expandable) {
		this.expandable = expandable;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	@JsonProperty
	public String getWidgetType() {
		return widgetType;
	}

}
