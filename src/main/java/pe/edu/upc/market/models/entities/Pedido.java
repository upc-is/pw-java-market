package pe.edu.upc.market.models.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "pedidos")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "tienda_id")
	private Tienda tienda;
	
	@Column(name = "precio_total", nullable = false)
	private Float precioTotal;
	
	@Column(name = "fecha_pedido", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaPedido;
	
	@Column(name = "medio_pago", length = 15, nullable = false)
	private String medioPago;
	
	private List<DetallePedido> detallePedidos;
}
