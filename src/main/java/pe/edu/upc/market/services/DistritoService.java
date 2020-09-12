package pe.edu.upc.market.services;

import java.util.List;

import pe.edu.upc.market.models.entities.Distrito;

public interface DistritoService extends CrudService<Distrito, Integer> {
	List<Distrito> findByNombre(String nombre) throws Exception;
}
