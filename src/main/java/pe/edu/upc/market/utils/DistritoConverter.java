package pe.edu.upc.market.utils;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import pe.edu.upc.market.controllers.ClienteView;
import pe.edu.upc.market.models.entities.Distrito;
import pe.edu.upc.market.services.DistritoService;

@FacesConverter("converter.DistritoConverter")
public class DistritoConverter implements Converter<Distrito> {
	
	@Inject
	private DistritoService distritoService;

	/*public DistritoConverter(DistritoService distritoService) {
		super();
		this.distritoService = distritoService;
	}*/

	@Override
	public Distrito getAsObject(FacesContext context, UIComponent component, String value) {
		System.out.println("NAUJ (getAsObject) : " + value);
		if(value != null && !value.isEmpty()) {
			try {
				Integer id = Integer.parseInt(value);
				System.out.println("NAUJ (getAsObject) id : " + id);
				Optional<Distrito> optional = distritoService.findById(id);
				if(optional.isPresent()) {
					return optional.get();
				}
				return null;
				/*Distrito distrito = new Distrito();
				distrito.setId(id);
				distrito.setNombre("Lima");
				distrito.setProvincia("Lima");
				
				return distrito;*/
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Distrito value) {
		if(value != null) {
			System.out.println("NAUJ (getAsString): " + value.getNombre());
			return String.valueOf(value.getId());
		} else {
			System.out.println("NAUJ (getAsString): NULL" );
			return "";
		}
	}

}
