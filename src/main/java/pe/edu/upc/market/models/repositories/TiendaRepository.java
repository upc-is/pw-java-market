package pe.edu.upc.market.models.repositories;

import java.util.List;

import pe.edu.upc.market.models.entities.Tienda;

public interface TiendaRepository extends JpaRepository<Tienda, Integer> {
	List<Tienda> findByNombre(String nombre) throws Exception;
}
