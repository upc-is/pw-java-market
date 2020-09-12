package pe.edu.upc.market.models.repositories;

import java.util.List;

import pe.edu.upc.market.models.entities.Distrito;

public interface DistritoRepository extends JpaRepository<Distrito, Integer> {
	List<Distrito> findByNombre(String nombre) throws Exception;
}
