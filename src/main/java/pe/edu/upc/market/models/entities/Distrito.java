package pe.edu.upc.market.models.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Table(name = "distritos")
public class Distrito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// AUTOINCREMENT DB
	private Integer id;
	
	@Column(name = "nombre", length = 25, nullable = false) // nullable = false  => NOT NULL
	private String nombre;
	
	@Column(name = "provincia", length = 15, nullable = false)
	private String Provincia; 
	
	@OneToMany(mappedBy = "distrito")
	private List<Cliente> clientes;
	
	@OneToMany(mappedBy = "distrito")
	private List<Tienda> tiendas;
	
	public Distrito( ) {
		clientes = new ArrayList<Cliente>();
		tiendas = new ArrayList<Tienda>();
	}
	
	public String getIdString() {
		return Integer.toString(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProvincia() {
		return Provincia;
	}

	public void setProvincia(String provincia) {
		Provincia = provincia;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Tienda> getTiendas() {
		return tiendas;
	}

	public void setTiendas(List<Tienda> tiendas) {
		this.tiendas = tiendas;
	}
		
}



