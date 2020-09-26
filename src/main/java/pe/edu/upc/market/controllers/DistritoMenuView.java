package pe.edu.upc.market.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pe.edu.upc.market.models.entities.Distrito;
import pe.edu.upc.market.services.DistritoService;

@Named("distritoMenuView")	// clienteView
//@ManagedBean("distritoMenuView")
@ViewScoped
public class DistritoMenuView implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Distrito> distritos;
	private Distrito distrito;
	
	@Inject
	private DistritoService distritoService;	
	
	@PostConstruct
	public void init() {
		loadDistritos();
	}
	
	public void loadDistritos( ) {
		try {
			this.distritos = distritoService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public List<Distrito> getDistritos() {
		return distritos;
	}

	public Distrito getDistrito() {
		return distrito;
	}
	
}
