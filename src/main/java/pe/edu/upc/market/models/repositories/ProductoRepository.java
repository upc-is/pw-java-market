package pe.edu.upc.market.models.repositories;

import java.util.List;

import pe.edu.upc.market.models.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>  {
	List<Producto> findByNombre(String nombre) throws Exception;
}
