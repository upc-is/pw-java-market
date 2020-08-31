package pe.edu.upc.market.models.entities;

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
@Table(name = "tiendas")
public class Tienda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(name = "nombre", length = 20, nullable = false)
	private String nombre;
	
	@Column(name = "direccion", length = 20, nullable = false)
	private String direccion;
	
	@ManyToOne
	@JoinColumn(name = "distrito_id")
	private Distrito distrito;
	
	
	private List<ProductoTienda> productoTiendas;
	
	@OneToMany(mappedBy = "tienda")
	private List<Pedido> pedidos;
}
