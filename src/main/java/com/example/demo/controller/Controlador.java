package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UsuariosDAO;
import com.example.demo.dao.ItemsViajeDAO;
import com.example.demo.dao.ViajesDAO;
import com.example.demo.entity.ItemsViaje;
import com.example.demo.entity.Usuarios;
import com.example.demo.entity.Viajes;

@RestController
@CrossOrigin(origins = "*")
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

	@PostMapping("/usuarios/login")
	public ResponseEntity<Object> loginUser(@RequestParam String correo, @RequestParam String contrasena) {
		System.out.println(correo + " " + contrasena);
		return ResponseEntity.ok(usuariosDAO.findByCorreoIgnoreCaseAndContrasena(correo, contrasena));
	}

	@PostMapping("/usuarios")
	public ResponseEntity<Usuarios> createUser(Usuarios usuario) {
		try {
			if (usuariosDAO.existsByCorreo(usuario.getCorreo())) {
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

	@GetMapping("/usuarios/{idusuarios}/viajes")
	public ResponseEntity<List<Viajes>> readAllViajesFromUsuario(@PathVariable int idusuarios) {
		return ResponseEntity.ok(viajesDAO.findAllByIdusuarios(idusuarios));
	}

	@PostMapping("/viajes")
	public ResponseEntity<Object> createViaje(Viajes viaje) {
		try {
			if (!usuariosDAO.existsById(viaje.getIdusuarios())) {
				return ResponseEntity.notFound().build();
			} else if (viaje.getFechainicio().compareTo(viaje.getFechafin()) > 0) {
				return generateResponse("Fechas caca", HttpStatus.NOT_ACCEPTABLE);
			}

			viajesDAO.save(viaje);
			return generateResponse("El viaje se guardó correctamente", HttpStatus.OK, viaje);
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

	@GetMapping("/viajes/{idviaje}/items")
	public ResponseEntity<List<ItemsViaje>> readAllItemsFromViaje(@PathVariable int idviaje) {
		return ResponseEntity.ok(itemsviajeDAO.findAllByIdviaje(idviaje));
	}

	@PostMapping("/items")
	public ResponseEntity<ItemsViaje> createUser(ItemsViaje itemsviaje) {
		try {
			if (!viajesDAO.existsById(itemsviaje.getIdviajes())) {
				return ResponseEntity.notFound().build();
			}

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

	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("status", status.value());

		return new ResponseEntity<Object>(map, status);
	}

	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("status", status.value());
		map.put("data", responseObj);

		return new ResponseEntity<Object>(map, status);
	}
}
