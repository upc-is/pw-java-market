package pe.edu.upc.market.models.entities;

import java.util.List;

public class Producto {
	private Integer Id;
	private String nombre;
	private String descripcion;	
	private String categoria;	
	
	private List<ProductoTienda> productoTiendas;
	private List<DetallePedido> detallePedidos;
	
}
