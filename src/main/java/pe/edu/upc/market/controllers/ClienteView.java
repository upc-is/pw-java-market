package pe.edu.upc.market.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

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
	// Objeto asociado al formulario del cliente
	private Cliente cliente;
	// Objeto asociado al rowSelect del datatable
	private Cliente clienteSelected;
	// Objeto asociado al search
	private Cliente clienteSearch;

	private List<Distrito> distritos;	
	private Distrito distritoSelected;	
	
	private Action action;
	// Style for panelGrid and dataTable
	private String stylePanelCliente;
	private String styleTableCliente;
	
	// Disables for commandButton
	private boolean disabledButtonNuevo;
	private boolean disabledButtonGrabar;
	private boolean disabledButtonCancelar;
	private boolean disabledButtonEditar;
	private boolean disabledButtonEliminar;
	
	// Text in Confirm Dialog
	private String messageConfirmDialog;
	
	@Inject
	private ClienteService clienteService;	

	@Inject
	private DistritoService distritoService;	

	@PostConstruct
	public void init() {
		this.clienteSearch = new Cliente();
		this.cleanForm();
		this.loadClientes();		
		this.loadDistritos();
		this.action = Action.NONE;
		this.stateList();
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
		this.clienteSelected = null;
	}
	
	// Metodo cuando se hace click en el boton Nuevo
	public void newCliente() {
		cleanForm();
		this.action = Action.NEW;
		this.stateNewEdit();
		this.addMessage("Hice click en Nuevo");
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
		boolean uniqueNumeroDocumento = true;
		if(this.action == Action.NEW || this.action == Action.EDIT) {
			try {
				// Para verificar que el número de documento es único
				Optional<Cliente> optional = clienteService.findByNumeroDocumento( cliente.getNumeroDocumento() );
				if(optional.isPresent()) {
					if(!optional.get().getId().equals(cliente.getId())) {
						uniqueNumeroDocumento = false;
					}
				}
				if(uniqueNumeroDocumento == true) {
					changeDistrito();
					if(this.action == Action.NEW) 
						clienteService.save(this.cliente);
					else
						clienteService.update(this.cliente);
					cleanForm();
					loadClientes();
					this.action = Action.NONE;
					this.stateList();
				}
				else {
					this.addMessage("El número de documento: " + cliente.getNumeroDocumento() + " ya existe");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}		
	}
	// Método cuando se hace click en el boton Cancelar
		public void cancelCliente() {
			cleanForm();
			this.stateList();
		}
	
	// Metodo que se ejecuta cuando el evento rowSelect ocurra
	public void selectCliente(SelectEvent<Cliente> e) {
		this.clienteSelected = e.getObject();
		this.messageConfirmDialog = this.clienteSelected.getNombresApellidos();
		this.stateSelectRow();
	}
	// Metodo que se ejecuta cuando el evento rowUnselect ocurra
	public void unselectCliente(UnselectEvent<Cliente> e) { 
		this.clienteSelected = null;
		this.stateList();
	}
	// Método que se ejecuta cuando hago click en el boton Editar
	public void editCliente() {
		if( this.clienteSelected != null ) {
			this.cliente = this.clienteSelected;
			this.action = Action.EDIT;
			this.stateNewEdit();
		}		
	}
	// Método que se ejecuta cuando hago click en el boton Eliminar
	public void deleteCliente() {
		if( this.clienteSelected != null ) {
			try {
				clienteService.deleteById( this.clienteSelected.getId());
				cleanForm();
				loadClientes();
				this.action = Action.NONE;
				this.stateList();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}
	public void findByNombresApellidos() {
		if(!this.clienteSearch.getNombresApellidos().isEmpty()) {
			try {
				this.clientes = clienteService.findByNombresApellidos( clienteSearch.getNombresApellidos() );
				this.stateList();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}
	public void findByNumeroDocumento() {
		if(!this.clienteSearch.getNumeroDocumento().isEmpty()) {
			try {
				this.clientes = new ArrayList<>();
				Optional<Cliente> optional = clienteService.findByNumeroDocumento( clienteSearch.getNumeroDocumento() );
				if(optional.isPresent()) {
					this.clientes.add(optional.get());
				}
				this.stateList();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}
	public void cleanByNombresApellidos() {
		this.clienteSearch.setNombresApellidos("");
		loadClientes();
		this.stateList();
	}
	public void cleanByNumeroDocumento() {
		this.clienteSearch.setNumeroDocumento("");
		loadClientes();
		this.stateList();
	}
	// State on Application
	public void stateList() {
		this.stylePanelCliente = "display:none;";
		this.styleTableCliente = "display:block;";
		this.disabledButtonNuevo = false;
		this.disabledButtonGrabar = true;
		this.disabledButtonCancelar = true;
		this.disabledButtonEditar = true;
		this.disabledButtonEliminar = true;
	}
	public void stateNewEdit() {
		this.stylePanelCliente = "display:block;";
		this.styleTableCliente = "display:none;";
		this.disabledButtonNuevo = true;
		this.disabledButtonGrabar = false;
		this.disabledButtonCancelar = false;
		this.disabledButtonEditar = true;
		this.disabledButtonEliminar = true;
	}
	public void stateSelectRow() {
		this.stylePanelCliente = "display:none;";
		this.styleTableCliente = "display:block;";
		this.disabledButtonNuevo = false;
		this.disabledButtonGrabar = true;
		this.disabledButtonCancelar = true;
		this.disabledButtonEditar = false;
		this.disabledButtonEliminar = false;
	}
	
	// Método quie muestra los mensajes
	public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
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

	public String getStylePanelCliente() {
		return stylePanelCliente;
	}

	public String getStyleTableCliente() {
		return styleTableCliente;
	}

	public boolean isDisabledButtonNuevo() {
		return disabledButtonNuevo;
	}

	public boolean isDisabledButtonGrabar() {
		return disabledButtonGrabar;
	}

	public boolean isDisabledButtonCancelar() {
		return disabledButtonCancelar;
	}

	public boolean isDisabledButtonEditar() {
		return disabledButtonEditar;
	}

	public boolean isDisabledButtonEliminar() {
		return disabledButtonEliminar;
	}

	public String getMessageConfirmDialog() {
		return messageConfirmDialog;
	}

	public Cliente getClienteSearch() {
		return clienteSearch;
	}
	
	
	
}
