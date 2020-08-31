package pe.edu.upc.market.models.entities;

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
	private Integer Id;
	
	@Column(name = "nombre", length = 25, nullable = false) // nullable = false  => NOT NULL
	private String nombre;
	
	@Column(name = "provincia", length = 15, nullable = false)
	private String Provincia; 
	
	@OneToMany(mappedBy = "distrito")
	private List<Cliente> clientes;
	
	@OneToMany(mappedBy = "distrito")
	private List<Tienda> tiendas;
}



