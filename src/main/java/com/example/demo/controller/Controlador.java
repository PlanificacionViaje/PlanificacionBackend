package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UsuariosDAO;
import com.example.demo.dao.ItemsViajeDAO;
import com.example.demo.dao.ViajesDAO;
import com.example.demo.entity.ItemsViaje;
import com.example.demo.entity.Usuarios;
import com.example.demo.entity.Viajes;

@RestController
@RequestMapping("/")
public class Controlador {
	@Autowired
	ItemsViajeDAO itemsviajeDAO;
	@Autowired
	UsuariosDAO usuariosDAO;
	@Autowired
	ViajesDAO viajesDAO;

	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Optional<Usuarios>> readUser(@PathVariable int id) {
		return ResponseEntity.ok(usuariosDAO.findById(id));
	}

	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuarios>> readAllUsers() {
		return ResponseEntity.ok(usuariosDAO.findAll());
	}

	@PostMapping("/usuarios")
	public ResponseEntity<Usuarios> createUser(Usuarios usuario) {
		try {
			if (usuariosDAO.existsById(usuario.getId())) {
				return ResponseEntity.notFound().build();
			}

			usuariosDAO.save(usuario);
			return ResponseEntity.ok(usuario);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/usuarios")
	public ResponseEntity<Usuarios> updateUser(Usuarios usuario) {
		try {
			if (!usuariosDAO.existsById(usuario.getId())) {
				return ResponseEntity.notFound().build();
			}

			usuariosDAO.save(usuario);
			return ResponseEntity.ok(usuario);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<Usuarios> deleteUser(@PathVariable int id) {
		if (!usuariosDAO.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		usuariosDAO.deleteById(id);
		return ResponseEntity.ok().build();
	}

	// CRUD Tabla viajes
	@GetMapping("/viajes")
	public ResponseEntity<List<Viajes>> readAllViajes() {
		return ResponseEntity.ok(viajesDAO.findAll());
	}

	@GetMapping("/viajes/{id}")
	public ResponseEntity<Optional<Viajes>> readViajes(@PathVariable int id) {
		return ResponseEntity.ok(viajesDAO.findById(id));
	}

	@PostMapping("/viajes/{idusuario}")
	public ResponseEntity<Viajes> createViaje(Viajes viaje, @PathVariable int idusuario) {
		try {
			if (!usuariosDAO.existsById(idusuario)) {
				return ResponseEntity.notFound().build();
			}
			viaje.setIdusuarios(idusuario);
			viajesDAO.save(viaje);
			return ResponseEntity.ok(viaje);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/viajes")
	public ResponseEntity<Viajes> updateViaje(Viajes viaje) {
		if (!viajesDAO.existsById(viaje.getId())) {
			return ResponseEntity.notFound().build();
		}

		viajesDAO.save(viaje);
		return ResponseEntity.ok(viaje);
	}

	@DeleteMapping("/viajes/{id}")
	public ResponseEntity<Viajes> deleteViaje(@PathVariable int id) {
		if (!viajesDAO.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		viajesDAO.deleteById(id);
		return ResponseEntity.ok().build();
	}

	// CRUD Tabla itemsviaje

	@GetMapping("/items")
	public ResponseEntity<List<ItemsViaje>> readAllItems() {
		return ResponseEntity.ok(itemsviajeDAO.findAll());
	}

	@GetMapping("/items/{id}")
	public ResponseEntity<Optional<ItemsViaje>> readItem(@PathVariable int id) {
		return ResponseEntity.ok(itemsviajeDAO.findById(id));
	}

	@PostMapping("/items/{idviajes}")
	public ResponseEntity<ItemsViaje> createUser(ItemsViaje itemsviaje, @PathVariable int idviajes) {
		try {
			if (!viajesDAO.existsById(idviajes)) {
				return ResponseEntity.notFound().build();
			}
			itemsviaje.setViajes_idviajes(idviajes);
			itemsviajeDAO.save(itemsviaje);
			return ResponseEntity.ok(itemsviaje);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/items/{id}")
	public ResponseEntity<ItemsViaje> deleteItem(@PathVariable int id) {
		if (!itemsviajeDAO.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		itemsviajeDAO.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/items")
	public ResponseEntity<ItemsViaje> updateItem(ItemsViaje itemsviaje) {
		if (!itemsviajeDAO.existsById(itemsviaje.getId())) {
			// return ResponseEntity.notFound().build();
			return ResponseEntity.ok(itemsviaje);
		}

		itemsviajeDAO.save(itemsviaje);
		return ResponseEntity.ok(itemsviaje);
	}
}
