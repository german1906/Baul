package com.itinajero.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itinajero.api.Entity.Usuario;

public interface IUsuarioServices{
	Page<Usuario> buscarTodos(Pageable pageable);
	Usuario guardar(Usuario usuario);
	void eliminarUsuario(int _id);
	Optional<Usuario> buscarPorId(int _id);
	ResponseEntity<Usuario> modificarUsuario(int _id);
	Usuario update(Usuario usuario);
}
