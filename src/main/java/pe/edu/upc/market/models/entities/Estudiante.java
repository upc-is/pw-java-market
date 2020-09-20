package pe.edu.upc.market.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "estudiantes")
public class Estudiante {
	@Id
	private Long id;
	
	@Column(length = 10)
	private String tiu;
	
	@Column(length = 30)
	private String carrera;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "id")	
	private Persona persona;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTiu() {
		return tiu;
	}

	public void setTiu(String tiu) {
		this.tiu = tiu;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
}
