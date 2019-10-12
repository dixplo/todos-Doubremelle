package s4.spring.ds1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Todos {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private int id;
	private String label;
	private String description;
	private Float avancement;
	private int poids;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Float getAvancement() {
		return avancement;
	}
	public void setAvancement(Float avancement) {
		this.avancement = avancement;
	}
	
	public int getPoids() {
		return poids;
	}
	public void setPoids(int poids) {
		this.poids = poids;
	}

}
