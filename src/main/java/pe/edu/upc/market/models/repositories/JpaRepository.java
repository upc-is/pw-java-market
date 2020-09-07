package pe.edu.upc.market.models.repositories;

import java.util.List;
import java.util.Optional;

// Interface base
public interface JpaRepository<T, ID> {
	T save(T entity) throws Exception;
	T update(T entity) throws Exception;
	void deleteById(ID id) throws Exception;
	Optional<T> findById(ID id) throws Exception;
	List<T> findAll() throws Exception;
}
