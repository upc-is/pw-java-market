package pe.edu.upc.market.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pe.edu.upc.market.models.entities.Estudiante;
import pe.edu.upc.market.services.EstudianteService;

@Named("estudianteView")	// estudianteView
@ViewScoped
public class EstudianteView implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Estudiante> estudiantes;
	
	@Inject
	private EstudianteService estudianteService;	
	
	@PostConstruct
	public void init() {
		this.loadEstudiantes();
	}
	
	public void loadEstudiantes() {
		try {
			this.estudiantes = estudianteService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	public EstudianteService getEstudianteService() {
		return estudianteService;
	}

	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}
	
	
	
}
