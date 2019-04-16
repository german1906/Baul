package com.itinajero.api.controller;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itinajero.api.Entity.Usuario;
import com.itinajero.api.services.IUsuarioServices;


@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public IUsuarioServices usuarioServices;
	
	
	
	// Método para traer todos los usuarios con paginación de a 3
	@GetMapping("/all")
	public ResponseEntity<Page<Usuario>> buscarTodosUsuarios(@PageableDefault(page=0, size = 20) Pageable pageable){
		   log.info("Getting all users with index: {}, and count: {}");
	       Page<Usuario> user = usuarioServices.buscarTodos(pageable);

	       if (user == null || user.isEmpty()) {
	           return new ResponseEntity<Page<Usuario>>(HttpStatus.NO_CONTENT);
	       }
	       return new ResponseEntity<Page<Usuario>>(user, HttpStatus.OK);
	}

	//Método para guardar un nuevo Usuario
	@PostMapping(path = "/create", consumes = "application/x-www-form-urlencoded")
	public Usuario crearUsuario(Usuario usuario) {
		usuarioServices.guardar(usuario);
		return usuario;
	}
	
	//Método para borrar un Usuario
	@DeleteMapping("/delete/{id}")
	public void eliminarUsuario(@PathVariable("id") int _id) {
		usuarioServices.eliminarUsuario(_id);
	}
	
	//Métofo para editar usuario
	@PutMapping(path = "/edit/{id}")
	public Usuario updateStudent(@PathVariable("id") int _id, @RequestBody Usuario usuario) {

		Optional<Usuario> usuarioEcontrado = usuarioServices.buscarPorId(_id);
		if (!usuarioEcontrado.isPresent())
			//return ResponseEntity.notFound().build();
			return usuario;
		int id = usuarioEcontrado.get().get_id();
		log.info("$$$$$$$$$$$$$$$esto encontro buscar por id$$$$$$$$$$$$");

		
		usuario.set_id(id);
		usuario = usuarioServices.guardar(usuario);

	   // return ResponseEntity.noContent().build();
		return usuario;
	}
	
	
	/*	/////////////////////Modelo para find all///////////////////////
   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<List<Fruit>> getAll(@RequestParam(value = "offset", defaultValue = "0") int index,
           @RequestParam(value = "numberOfRecord", defaultValue = "10") int numberOfRecord) {
       LOG.info("Getting all fruits with index: {}, and count: {}", index, numberOfRecord);
       List<Fruit> fruits = fruitService.getAll(index, numberOfRecord);

       if (fruits == null || fruits.isEmpty()) {
           return new ResponseEntity<List<Fruit>>(HttpStatus.NO_CONTENT);
       }

       return new ResponseEntity<List<Fruit>>(fruits, HttpStatus.OK);
   } 
	 */	
	
}
