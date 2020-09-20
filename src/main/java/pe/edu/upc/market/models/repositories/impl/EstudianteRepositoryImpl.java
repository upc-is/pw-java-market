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

import pe.edu.upc.market.models.entities.Estudiante;
import pe.edu.upc.market.models.repositories.EstudianteRepository;

@Named
@ApplicationScoped
public class EstudianteRepositoryImpl implements EstudianteRepository, Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "MarketPU")
	private EntityManager em;

	@Override
	public Estudiante save(Estudiante entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public Estudiante update(Estudiante entity) throws Exception {
		em.merge(entity);
		return entity;
	}

	@Override
	public void deleteById(Long id) throws Exception {
		Optional<Estudiante> optional = findById(id);
		if( optional.isPresent() ) {
			em.remove( optional.get() );
		}		
	}

	@Override
	public Optional<Estudiante> findById(Long id) throws Exception {
		Optional<Estudiante> optional = Optional.empty();		
		
		String qlString = "SELECT d FROM Estudiante d WHERE d.id = ?1";
		TypedQuery<Estudiante> query = em.createQuery(qlString, Estudiante.class);
		query.setParameter(1, id);
		
		Estudiante estudiante = query.getSingleResult();
		if(estudiante != null) {
			optional = Optional.of(estudiante);
		}		
		return optional;
	}

	@Override
	public List<Estudiante> findAll() throws Exception {
		List<Estudiante> estudiantes = new ArrayList<>();
		
		String qlString = "SELECT d FROM Estudiante d";
		TypedQuery<Estudiante> query = em.createQuery(qlString, Estudiante.class);

		estudiantes = query.getResultList();
		return estudiantes;
	}

}