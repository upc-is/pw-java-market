package pe.edu.upc.market.models.entities;

import java.util.Date;
import java.util.List;

public class Pedido {
	private Integer Id;
	private Cliente cliente;
	private Tienda tienda;
	private Float precioTotal;
	private Date fechaPedido;	
	private String medioPago;
	
	private List<DetallePedido> detallePedidos;
}
