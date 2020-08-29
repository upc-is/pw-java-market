package pe.edu.upc.market.models.entities;

import java.util.List;

public class Tienda {
	private Integer Id;
	private String nombre;
	private String direccion;
	private Distrito distrito;
	
	private List<ProductoTienda> productoTiendas;
	private List<Pedido> pedidos;
}
