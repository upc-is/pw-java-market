package pe.edu.upc.market.services;

import java.util.List;

import pe.edu.upc.market.models.entities.Producto;

public interface ProductoService extends CrudService<Producto, Integer> {
	List<Producto> findByNombre(String nombre) throws Exception;
}
