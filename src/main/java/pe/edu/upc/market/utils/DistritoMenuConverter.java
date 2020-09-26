package pe.edu.upc.market.utils;

import java.io.Serializable;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pe.edu.upc.market.controllers.DistritoMenuView;
import pe.edu.upc.market.models.entities.Distrito;

//@ViewScoped
@FacesConverter("distritoMenuConverter")
//@Named(value = "distritoMenuConverter")
//@ViewScoped
public class DistritoMenuConverter implements Converter<Distrito> , Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private DistritoMenuView distritoMenuView;
	
	@Override
	public Distrito getAsObject(FacesContext context, UIComponent component, String value) {
		System.out.println("NAUJ (getAsObject) : " + value);
		System.out.println("NAUJ (getAsObject) LIST : " + distritoMenuView);
		System.out.println("NAUJ (getAsObject) LIST : " + distritoMenuView.getDistritos());
		if (value == null) {
            return null;
        }
		return null;
        //return fromSelect(component, value);
	}
	
	/*@Override
	public Distrito getAsObject(FacesContext context, UIComponent component, String value) {
		DistritoMenuView distritoMenuView = (DistritoMenuView) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("distritoMenuView");
	    //DealBean dealBean = (DealBean)  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("dealBean");
	    if (distritoMenuView != null) {
	    	List<Distrito> distritos = distritoMenuView.getDistritos();
	        for (Distrito distrito : distritos) {
	            if (value.equals( String.valueOf(distrito.getId()) )) {
	            	System.out.println("NAUJ (getAsObject) : " + distrito.getNombre());
	                return distrito;
	            }
	        }
	    }
	    System.out.println("NAUJ (getAsObject) : NULO " );
	    return null;
	}*/
	
	/*private Distrito fromSelect(final UIComponent component, final String value) {
		 
        if (component.getClass() == UISelectItem.class) {
        	System.out.println("NAUJ (getAsObject) : ENTRO if1");
            final UISelectItem item = (UISelectItem) component;
            System.out.println("NAUJ (getAsObject) : objeto: " + item.getValue());
            final Distrito distrito = (Distrito)item.getValue();
            
            if (value.equals( String.valueOf(distrito.getId()) )) {
            	System.out.println("NAUJ (getAsObject) : " + distrito.getNombre());
                return distrito;
            }
        }
 
        if (component.getClass() == UISelectItems.class) {
        	System.out.println("NAUJ (getAsObject) : ENTRO if 2" );
            final UISelectItems items = (UISelectItems) component;
            final List<Distrito> distritos = (List<Distrito>) items.getValue();
            for (final Distrito distrito : distritos) {
            	if (value.equals( String.valueOf(distrito.getId()) )) {
            		System.out.println("NAUJ (getAsObject) : " + distrito.getNombre());
                    return distrito;
                }
            }
        } 
 
        if (!component.getChildren().isEmpty()) {
        	System.out.println("NAUJ (getAsObject) : ENTRO if 3" );
            for (final UIComponent componentChildren : component.getChildren()) {
                final Distrito result = fromSelect(componentChildren, value);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }*/

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
