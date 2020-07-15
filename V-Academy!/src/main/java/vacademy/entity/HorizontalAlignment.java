
package vacademy.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "HorizontalAlignment")
public class HorizontalAlignment implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "id", nullable = false)
	@Id
	protected int id;

	@Column(name = "name", nullable = false)
	protected String name;

	
	public int getId() {
		return id;
	}

	
	public String getName() {
		return name;
	}

	
	public void setId(int id) {
		this.id = id;
	}

		public void setName(String name) {
		this.name = name;
	}

}
