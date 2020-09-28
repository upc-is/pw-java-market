package pe.edu.upc.market.models.entities;
/*Entity
repository
Services(Business)
Controller
JSF -> Front*/
// int -> Integer

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name = "clientes") // Snake case	// Plural
public class Cliente {	// upper Cammel case
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombres_apellidos", length = 50, nullable = false)
	private String nombresApellidos; // lower cammel case
	
	@Column(name = "numero_documento", length = 12, nullable = false)
	private String numeroDocumento;
	
	@Column(name = "direccion", length = 20, nullable = false)
	private String direccion;
	
	@ManyToOne
	@JoinColumn(name = "distrito_id")
	private Distrito distrito;
	
	@Transient
	private Integer distritoId;
	
	@Column(name = "fecha_nacimiento")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	@Column(name = "telefono", length = 9, nullable = false)
	private String telefono;
	
	@Column(name = "email", length = 30, nullable = false)
	private String email;
	
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;
	
	public Cliente() {
		pedidos = new ArrayList<Pedido>();
		this.distritoId = 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombresApellidos() {
		return nombresApellidos;
	}

	public void setNombresApellidos(String nombresApellidos) {
		this.nombresApellidos = nombresApellidos;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
		if (this.distrito != null) {
			this.distritoId = this.distrito.getId();
		}
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Integer getDistritoId() {
		if(this.distritoId <= 0 && this.distrito != null) {
			this.distritoId = this.distrito.getId();
		}
		return distritoId;
	}

	public void setDistritoId(Integer distritoId) {
		this.distritoId = distritoId;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	
}
