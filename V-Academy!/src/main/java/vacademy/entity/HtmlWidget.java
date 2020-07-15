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
@Table(name = "HtmlWidget")
@PrimaryKeyJoinColumn(referencedColumnName = "widgetId")
public class HtmlWidget extends Widget {

	private static final long serialVersionUID = 4433705202573913995L;

	
	static final Integer MAX_CHARS = 500;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "htmlId", updatable = false, nullable = false)
	private int htmlId;

	@Column(name = "markupText")
	private String markupText;

	@Column(name = "maxCharacters")
	private Integer maxCharacters = MAX_CHARS;
	
	@JsonIgnore
	@Transient
	private final String widgetType = "htmlwidget";
	
	
	public int getHtmlId() {
		return htmlId;
	}

	
	public void setHtmlId(int htmlId) {
		this.htmlId = htmlId;
	}

	public String getMarkupText() {
		return markupText;
	}

	
	public void setMarkupText(String markupText) {
		this.markupText = markupText;
	}

	
	public Integer getMaxCharacters() {
		return maxCharacters;
	}

	
	public void setMaxCharacters(Integer maxCharacters) {
		this.maxCharacters = maxCharacters;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	@JsonProperty
	public String getWidgetType() {
		return widgetType;
	}

}
