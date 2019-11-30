package duoc.portafolio.feriavirtual.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import duoc.portafolio.feriavirtual.commons.GenericServiceImpl;
import duoc.portafolio.feriavirtual.models.Perfil;
import duoc.portafolio.feriavirtual.repository.PerfilRepository;
import duoc.portafolio.feriavirtual.service.PerfilService;

@Service
public class PerfilServiceImpl extends GenericServiceImpl<Perfil, Integer> implements PerfilService{

	@Autowired
	private PerfilRepository perfilRepository;
	
	@Override
	public CrudRepository<Perfil, Integer> getRepository() {
		return perfilRepository;
	}
}