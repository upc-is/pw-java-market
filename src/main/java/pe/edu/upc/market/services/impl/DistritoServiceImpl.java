package pe.edu.upc.market.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.edu.upc.market.models.entities.Distrito;
import pe.edu.upc.market.models.repositories.DistritoRepository;
import pe.edu.upc.market.services.DistritoService;

@Named
@ApplicationScoped
public class DistritoServiceImpl implements DistritoService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private DistritoRepository distritoRepository;

	@Transactional
	@Override
	public Distrito save(Distrito entity) throws Exception {
		return distritoRepository.save(entity);
	}

	@Transactional
	@Override
	public Distrito update(Distrito entity) throws Exception {
		return distritoRepository.update(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		distritoRepository.deleteById(id);
	}

	@Override
	public Optional<Distrito> findById(Integer id) throws Exception {
		return distritoRepository.findById(id);
	}

	@Override
	public List<Distrito> findAll() throws Exception {
		return distritoRepository.findAll();
	}

	@Override
	public List<Distrito> findByNombre(String nombre) throws Exception {
		return distritoRepository.findByNombre(nombre);
	}
}
