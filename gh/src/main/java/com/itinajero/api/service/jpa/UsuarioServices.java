package com.itinajero.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.itinajero.api.Entity.Usuario;
import com.itinajero.api.repository.UsuarioRepository;
import com.itinajero.api.services.IUsuarioServices;



@Service
public class UsuarioServices implements IUsuarioServices {
	
	@Autowired
	public UsuarioRepository usuarioRepository;
	
	/*@Autowired
	public BCryptPasswordEncoder BCryptPasswordEncoder;
	*/
	
	@Override
	public Page<Usuario> buscarTodos(Pageable pageable) {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll(pageable);
	}

	@Override
	public Usuario guardar(Usuario usuario) {
		//usuario.setPassword(BCryptPasswordEncoder.encode(usuario.getPassword()));
		usuarioRepository.save(usuario);	
		return usuario;
	}
	
	@Override
	public void eliminarUsuario(int _id) {
		usuarioRepository.deleteById(_id);
	}
	
	@Override
	public Optional<Usuario> buscarPorId(int _id) {
		Optional<Usuario> usuario = usuarioRepository.findById(_id);
		return usuario;
	}
	
	@Override
	public ResponseEntity<Usuario> modificarUsuario(int _id) {
		Optional<Usuario> optional = usuarioRepository.findById(_id);
	    return ResponseEntity.notFound().build();
	}
	
	@Override
	public Usuario update(Usuario usuario) {
		usuarioRepository.saveAndFlush(usuario);
		return null;
	}
	
	/*

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateById(@PathVariable("id") Long id, @RequestBody Customer customer) {

    }
	
	/*
	@Override
	public Usuario buscarPorId1(int _id) {	
		Optional<Pelicula> optional = peliculasRepo.findById(idPelicula);
		if (optional.isPresent())
			return optional.get();
		return null;
	}

	@Override
	public void eliminar(int idPelicula) {
		//peliculasRepo.delete(idPelicula); // Spring 4.3
		peliculasRepo.deleteById(idPelicula);
		
	}
	*/

}
