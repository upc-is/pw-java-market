package pe.edu.upc.market.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import pe.edu.upc.market.models.entities.Cliente;
import pe.edu.upc.market.models.entities.Distrito;
import pe.edu.upc.market.services.ClienteService;
import pe.edu.upc.market.services.DistritoService;
import pe.edu.upc.market.utils.Action;

@Named("clienteView")	// clienteView
@ViewScoped
public class ClienteView implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Cliente> clientes;
	private Cliente cliente;
	private Cliente clienteSelected;

	private List<Distrito> distritos;	
	private Distrito distritoSelected;	
	
	private Action action;
	
	@Inject
	private ClienteService clienteService;	

	@Inject
	private DistritoService distritoService;	
	
	
	
	@PostConstruct
	public void init() {
		this.cleanForm();
		this.loadClientes();		
		this.loadDistritos();
		this.action = Action.NONE;
	}

	public void loadClientes() {
		try {
			this.clientes = clienteService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void loadDistrito( Integer id ) {
		try {
			this.distritoSelected = (distritoService.findById(id)).get();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	public void loadDistritos( ) {
		try {
			this.distritos = distritoService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void cleanForm( ) {
		this.cliente = new Cliente();
	}
	
	// Metodo cuando se hace click en el boton Nuevo
	public void newCliente() {
		cleanForm();
		this.action = Action.NEW;
	}
	
	// Funciona cuando se cambia el distrito
	public void changeDistrito() {
		if (this.cliente.getDistrito() != null) {
			if( !this.cliente.getDistrito().getId().equals( this.cliente.getDistritoId() ) ) {
				loadDistrito( this.cliente.getDistritoId() );
				this.cliente.setDistrito(this.distritoSelected);
			}
		} // Cuando es un nuevo Cliente
		else {
			loadDistrito( this.cliente.getDistritoId() );
			this.cliente.setDistrito(this.distritoSelected);
		}
	}
	// Método cuando se hace click en el boton Grabar
	public void saveCliente() {
		if(this.action == Action.NEW || this.action == Action.EDIT) {
			try {
				changeDistrito();
				if(this.action == Action.NEW) 
					clienteService.save(this.cliente);
				else
					clienteService.update(this.cliente);
				cleanForm();
				loadClientes();
				this.action = Action.NONE;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}		
	}
	
	// Metodo que se ejecuta cuando el evento rowSelect ocurra
	public void selectCliente(SelectEvent<Cliente> e) {
		this.clienteSelected = e.getObject();
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<Distrito> getDistritos() {
		return distritos;
	}

	public DistritoService getDistritoService() {
		return distritoService;
	}
	
	
}
