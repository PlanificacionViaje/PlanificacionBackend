package com.example.demo.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ItemsViajeDAO;
import com.example.demo.dao.UsuariosDAO;
import com.example.demo.entity.ItemsViaje;

@RestController
@RequestMapping("/")
public class Controlador {
	@Autowired
	ItemsViajeDAO itemsviajeDAO;
	UsuariosDAO usuariosDAO;

	@GetMapping("/items/{idusuario}")
	public ResponseEntity<Optional<ItemsViaje>> readItem(@PathVariable int id) {
		return ResponseEntity.ok(itemsviajeDAO.findById(id));
	}

	// Tengo que sustituir todos los idusuario por el idviaje, para eso necesito la
	// clase viajes
	@PostMapping("/items/")
	public ResponseEntity<ItemsViaje> createUser(ItemsViaje itemsviaje, @PathVariable int idusuario) {
		try {
			if (!usuariosDAO.existsById(idusuario)) {
				return ResponseEntity.notFound().build();
			}
			// if (itemsviajeDAO.existsById(itemsviaje.getId())) {
			// return ResponseEntity.notFound().build();
			// }
			itemsviaje.setViajes_idviajes(idusuario);
			itemsviajeDAO.save(itemsviaje);
			return ResponseEntity.ok(itemsviaje);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	// @PostMapping("/viajes/{idusuario}")
	// public ResponseEntity<Viajes> createViaje(Viajes viaje,@PathVariable int
	// idusuario) {
	// try {
	// if (!usuariosDAO.existsById(idusuario)) {
	// return ResponseEntity.notFound().build();
	// }
	// viaje.setUsuarios_idUsuarios(idusuario);
	// viajesDAO.save(viaje);
	// return ResponseEntity.ok(viaje);
	// } catch (Exception e) {
	// return ResponseEntity.notFound().build();
	// }
	// }

}
