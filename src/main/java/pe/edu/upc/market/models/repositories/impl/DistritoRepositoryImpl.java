package pe.edu.upc.market.models.repositories.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.edu.upc.market.models.entities.Distrito;
import pe.edu.upc.market.models.repositories.DistritoRepository;

@Named
@ApplicationScoped
public class DistritoRepositoryImpl implements DistritoRepository, Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "MarketPU")
	private EntityManager em;

	@Override
	public Distrito save(Distrito entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public Distrito update(Distrito entity) throws Exception {
		em.merge(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		Optional<Distrito> optional = findById(id);
		if( optional.isPresent() ) {
			em.remove( optional.get() );
		}		
	}

	@Override
	public Optional<Distrito> findById(Integer id) throws Exception {
		Optional<Distrito> optional = Optional.empty();		
		
		String qlString = "SELECT d FROM Distrito d WHERE d.id = ?1";
		TypedQuery<Distrito> query = em.createQuery(qlString, Distrito.class);
		query.setParameter(1, id);
		
		Distrito distrito = query.getSingleResult();
		if(distrito != null) {
			optional = Optional.of(distrito);
		}		
		return optional;
	}

	@Override
	public List<Distrito> findAll() throws Exception {
		List<Distrito> distritos = new ArrayList<>();
		
		String qlString = "SELECT d FROM Distrito d";
		TypedQuery<Distrito> query = em.createQuery(qlString, Distrito.class);

		distritos = query.getResultList();
		return distritos;
	}

	@Override
	public List<Distrito> findByNombre(String nombre) throws Exception {
		List<Distrito> distritos = new ArrayList<>();
		String qlString = "SELECT d FROM Distrito d WHERE d.nombre LIKE '%?1%'";
		TypedQuery<Distrito> query = em.createQuery(qlString, Distrito.class);
		query.setParameter(1, nombre);
		distritos = query.getResultList();
		return distritos;
	}

}
