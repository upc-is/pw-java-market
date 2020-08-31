package pe.edu.upc.market.models.entities;
/*Entity
repository
Services(Business)
Controller
JSF -> Front*/
// int -> Integer

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name = "clientes") // Snake case	// Plural
public class Cliente {	// upper Cammel case
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(name = "nombres_apellidos", length = 50, nullable = false)
	private String nombresApellidos; // lower cammel case
	
	@Column(name = "numero_documento", length = 12, nullable = false)
	private String numeroDocumento;
	
	@Column(name = "direccion", length = 20, nullable = false)
	private String direccion;
	
	@ManyToOne
	@JoinColumn(name = "distrito_id")
	private Distrito distrito;
	
	@Column(name = "telefono", length = 9, nullable = false)
	private String telefono;
	
	@Column(name = "email", length = 30, nullable = false)
	private String email;
	
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;
}
