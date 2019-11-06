package duoc.portafolio.feriavirtual.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import duoc.portafolio.feriavirtual.commons.GenericServiceImpl;
import duoc.portafolio.feriavirtual.models.Usuario;
import duoc.portafolio.feriavirtual.repository.UsuarioRepository;
import duoc.portafolio.feriavirtual.service.UsuarioService;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Integer> implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public CrudRepository<Usuario, Integer> getRepository() {
		return usuarioRepository;
	}
}