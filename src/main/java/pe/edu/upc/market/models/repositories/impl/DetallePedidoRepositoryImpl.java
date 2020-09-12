package pe.edu.upc.market.models.repositories.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.edu.upc.market.models.entities.DetallePedido;
import pe.edu.upc.market.models.repositories.DetallePedidoRepository;

@Named
public class DetallePedidoRepositoryImpl implements DetallePedidoRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "MarketPU")
	private EntityManager em;
	
	@Override
	public DetallePedido save(DetallePedido entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public DetallePedido update(DetallePedido entity) throws Exception {
		em.merge(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		Optional<DetallePedido> optional = findById(id);
		if( optional.isPresent() ) {
			em.remove( optional.get() );
		}		
	}

	@Override
	public Optional<DetallePedido> findById(Integer id) throws Exception {
		Optional<DetallePedido> optional = Optional.empty();		
		
		String qlString = "SELECT dp FROM DetallePedido dp WHERE dp.id = ?1";
		TypedQuery<DetallePedido> query = em.createQuery(qlString, DetallePedido.class);
		query.setParameter(1, id);
		
		DetallePedido detallePedido = query.getSingleResult();
		if(detallePedido != null) {
			optional = Optional.of(detallePedido);
		}		
		return optional;
	}

	@Override
	public List<DetallePedido> findAll() throws Exception {
		List<DetallePedido> detallePedidos = new ArrayList<>();
		
		String qlString = "SELECT dp FROM DetallePedido dp";
		TypedQuery<DetallePedido> query = em.createQuery(qlString, DetallePedido.class);

		detallePedidos = query.getResultList();
		return detallePedidos;
	}

}
