package pe.edu.upc.market.services;

import java.util.List;

import pe.edu.upc.market.models.entities.Tienda;

public interface TiendaService extends CrudService<Tienda, Integer> {
	List<Tienda> findByNombre(String nombre) throws Exception;
}
