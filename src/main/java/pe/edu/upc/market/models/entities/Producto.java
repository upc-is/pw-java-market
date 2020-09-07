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
@Table(name = "productos")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;
	
	@Column(name = "descripcion", length = 50, nullable = false)
	private String descripcion;	
	
	@Column(name = "categoria", length = 15, nullable = false)
	private String categoria;	
	
	@OneToMany(mappedBy = "producto")
	private List<ProductoTienda> productoTiendas;
	
	@OneToMany(mappedBy = "producto")
	private List<DetallePedido> detallePedidos;
	
	public Producto() {
		productoTiendas = new ArrayList<ProductoTienda>();
		detallePedidos = new ArrayList<DetallePedido>();
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<ProductoTienda> getProductoTiendas() {
		return productoTiendas;
	}

	public void setProductoTiendas(List<ProductoTienda> productoTiendas) {
		this.productoTiendas = productoTiendas;
	}

	public List<DetallePedido> getDetallePedidos() {
		return detallePedidos;
	}

	public void setDetallePedidos(List<DetallePedido> detallePedidos) {
		this.detallePedidos = detallePedidos;
	}
	
}
