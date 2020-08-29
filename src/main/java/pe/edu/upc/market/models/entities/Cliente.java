package pe.edu.upc.market.models.entities;
/*Entity
repository
Services(Business)
Controller
JSF -> Front*/
// int -> Integer

import java.util.List;

public class Cliente {	// upper Cammel case
	private Integer Id;
	private String nombresApellidos; // lower cammel case
	private String numeroDocumento;
	private String direccion;
	private Distrito distrito;
	private String telefono;
	private String email;
	
	private List<Pedido> pedidos;
}
