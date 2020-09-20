package pe.edu.upc.market.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.edu.upc.market.models.entities.Estudiante;
import pe.edu.upc.market.models.repositories.EstudianteRepository;
import pe.edu.upc.market.services.EstudianteService;

@Named
@ApplicationScoped
public class EstudianteServiceImpl implements EstudianteService, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstudianteRepository estudianteRepository;
	
	@Transactional
	@Override
	public Estudiante save(Estudiante entity) throws Exception {
		return estudianteRepository.save(entity);
	}

	@Transactional
	@Override
	public Estudiante update(Estudiante entity) throws Exception {
		return estudianteRepository.update(entity);
	}

	@Transactional
	@Override
	public void deleteById(Long id) throws Exception {
		estudianteRepository.deleteById(id);
	}

	@Override
	public Optional<Estudiante> findById(Long id) throws Exception {
		return estudianteRepository.findById(id);
	}

	@Override
	public List<Estudiante> findAll() throws Exception {
		return estudianteRepository.findAll();
	}

}
