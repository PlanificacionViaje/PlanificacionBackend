package com.example.demo.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public ResponseEntity<Object> readUser(@PathVariable int id) {
		try {
			Optional<Usuarios> usuario = usuariosDAO.findById(id);
			
			if (!usuario.isPresent()) {
				return generateResponse("El usuario no existe.", HttpStatus.NOT_FOUND);
			} else {
				return generateResponse("Se ha encontrado la información del usuario.", HttpStatus.OK, usuario);
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/usuarios")
	public ResponseEntity<Object> readAllUsers() {
		try {
			return generateResponse("Lista de todos los usuarios.", HttpStatus.OK, usuariosDAO.findAll());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping("/usuarios/login")
	public ResponseEntity<Object> loginUser(@RequestParam String correo, @RequestParam String contrasena) {
		try {
			Optional<Usuarios> usuario = usuariosDAO.findByCorreoIgnoreCaseAndContrasena(correo, contrasena);

			if (!usuario.isPresent()) {
				return generateResponse("El correo o la contraseña son incorrectos.", HttpStatus.BAD_REQUEST);
			}

			return generateResponse("Se ha hecho login correctamente.", HttpStatus.OK, usuario);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping("/usuarios")
	public ResponseEntity<Object> createUser(Usuarios usuario) {
		try {
			String regex = "^(.+)@(.+)$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(usuario.getCorreo());

			if (usuariosDAO.existsByCorreo(usuario.getCorreo())) {
				return generateResponse("El correo proporcionado ya está en uso.", HttpStatus.BAD_REQUEST);
			} else if (usuariosDAO.existsById(usuario.getId())) {
				return generateResponse("Ya existe un usuario con ese identificador.", HttpStatus.BAD_REQUEST);
			} else if (!matcher.matches()) {
				return generateResponse("El correo introducido no es válido.", HttpStatus.BAD_REQUEST);
			} else if (usuario.getContrasena().isEmpty()) {
				return generateResponse("La contraseña introducida no es válida.", HttpStatus.BAD_REQUEST);
			} else if (usuario.getNombre().isEmpty()) {
				return generateResponse("El nombre introducido no es válido.", HttpStatus.BAD_REQUEST);
			} else if (usuario.getNombre().length() > 45) {
				return generateResponse("El nombre es demasiado largo.", HttpStatus.BAD_REQUEST);
			}

			usuariosDAO.save(usuario);
			return generateResponse("Registro completado", HttpStatus.CREATED, usuario);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/usuarios")
	public ResponseEntity<Object> updateUser(Usuarios usuario) {
		try {
			if (!usuariosDAO.existsById(usuario.getId())) {
				return generateResponse("El usuario no existe.", HttpStatus.NOT_FOUND);
			}

			usuariosDAO.save(usuario);
			return generateResponse("Datos del usuario modificados correctamente.", HttpStatus.OK, usuario);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) {
		try {
			if (!usuariosDAO.existsById(id)) {
				return generateResponse("El usuario no existe.", HttpStatus.NOT_FOUND);
			}

			usuariosDAO.deleteById(id);
			return generateResponse("El usuario se ha eliminado correctamente.", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	// CRUD Tabla viajes
	@GetMapping("/viajes")
	public ResponseEntity<Object> readAllViajes() {
		try {
			return generateResponse("Lista de todos los viajes.", HttpStatus.OK, viajesDAO.findAll());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/viajes/{id}")
	public ResponseEntity<Object> readViajes(@PathVariable int id) {
		try {
			Optional<Viajes> viaje = viajesDAO.findById(id);

			if (!viaje.isPresent()) {
				return generateResponse("El viaje no existe.", HttpStatus.NOT_FOUND);
			} else {
				return generateResponse("Se ha encontrado la información del viaje.", HttpStatus.OK, viaje);
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/usuarios/{idusuarios}/viajes")
	public ResponseEntity<Object> readAllViajesFromUsuario(@PathVariable int idusuarios) {
		try {
			if (usuariosDAO.existsById(idusuarios)) {
				return generateResponse("Lista de viajes del usuario.", HttpStatus.OK, viajesDAO.findAllByIdusuariosOrderByFechainicio(idusuarios));
			} else {
				return generateResponse("El usuario no existe.", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/usuarios/{idusuarios}/proximosviajes")
	public ResponseEntity<Object> readUpcomingTripsFromUsuario(@PathVariable int idusuarios) {
		try {
			if (usuariosDAO.existsById(idusuarios)) {
					java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
				return generateResponse("Próximos viajes del usuario.", HttpStatus.OK, viajesDAO.findAllByIdusuariosAndFechainicioAfterOrderByFechainicio(idusuarios, date));
			} else {
				return generateResponse("El usuario no existe.", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping("/viajes")
	public ResponseEntity<Object> createViaje(Viajes viaje) {
		try {
			if (!usuariosDAO.existsById(viaje.getIdusuarios())) {
				return generateResponse("No se puede crear el viaje porque el usuario proporcionado no existe.", HttpStatus.BAD_REQUEST);
			} else if (viajesDAO.existsById(viaje.getId())) {
				return generateResponse("Ya existe un viaje con ese identificador.", HttpStatus.BAD_REQUEST);
			} else if (viaje.getFechainicio().compareTo( new java.sql.Date(Calendar.getInstance().getTime().getTime())) < 0) {
				return generateResponse("No puedes crear un viaje al pasado.", HttpStatus.BAD_REQUEST);
			} else if (viaje.getFechainicio().compareTo(viaje.getFechafin()) > 0) {
				return generateResponse("La fecha de fin del viaje no puede ser menor a la de inicio.", HttpStatus.BAD_REQUEST);
			} else if (viaje.getNombre().isEmpty()) {
				return generateResponse("El nombre del viaje debe contener texto.", HttpStatus.BAD_REQUEST);
			} else if (viaje.getDescripcion().length() > 500) {
				return generateResponse("La descripción es demasiado larga.", HttpStatus.BAD_REQUEST);
			} else if (viaje.getNombre().length() > 45) {
				return generateResponse("El nombre es demasiado largo.", HttpStatus.BAD_REQUEST);
			}

			viajesDAO.save(viaje);
			return generateResponse("El viaje se ha creado correctamente", HttpStatus.OK, viaje);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/viajes")
	public ResponseEntity<Object> updateViaje(Viajes viaje) {
		try {
			if (!viajesDAO.existsById(viaje.getId())) {
				return generateResponse("El viaje no existe.", HttpStatus.NOT_FOUND);
			} else if (viaje.getIdusuarios() != viajesDAO.findById(viaje.getId()).get().getIdusuarios()) {
				return generateResponse("No se puede cambiar el dueño del viaje.", HttpStatus.BAD_REQUEST);
			} else if (viaje.getFechainicio().compareTo(viaje.getFechafin()) > 0) {
				return generateResponse("La fecha de fin del viaje no puede ser menor a la de inicio.", HttpStatus.BAD_REQUEST);
			}

			viajesDAO.save(viaje);
			return generateResponse("Datos del viaje modificados correctamente.", HttpStatus.OK, viaje);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/viajes/{id}")
	public ResponseEntity<Object> deleteViaje(@PathVariable int id) {
		try {
			if (!viajesDAO.existsById(id)) {
				return generateResponse("El viaje no existe.", HttpStatus.NOT_FOUND);
			}
			viajesDAO.deleteById(id);
			return generateResponse("El viaje se ha eliminado correctamente.", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	// CRUD Tabla itemsviaje

	@GetMapping("/items")
	public ResponseEntity<Object> readAllItems() {
		try {
			return generateResponse("Lista de todos los items.", HttpStatus.OK, itemsviajeDAO.findAll());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/items/{id}")
	public ResponseEntity<Object> readItem(@PathVariable int id) {
		try {
			Optional<ItemsViaje> item = itemsviajeDAO.findById(id);

			if (!item.isPresent()) {
				return generateResponse("El item no existe.", HttpStatus.NOT_FOUND);
			} else {
				return generateResponse("Se ha encontrado la información del item.", HttpStatus.OK, item);
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/viajes/{idviaje}/items")
	public ResponseEntity<Object> readAllItemsFromViaje(@PathVariable int idviaje) {
		try {
			if (viajesDAO.existsById(idviaje)) {
				return generateResponse("Lista de items del vaje.", HttpStatus.OK, itemsviajeDAO.findAllByIdviaje(idviaje));
			} else {
				return generateResponse("El viaje no existe.", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping("/items")
	public ResponseEntity<Object> createItem(ItemsViaje itemsviaje) {
		try {
			Optional<Viajes> viaje = viajesDAO.findById(itemsviaje.getIdviajes());

			if (!viaje.isPresent()) {
				return generateResponse("No se puede crear el item porque el viaje proporcionado no existe.", HttpStatus.BAD_REQUEST);
			} else if (itemsviajeDAO.existsById(itemsviaje.getId())) {
				return generateResponse("Ya existe un item con ese identificador.", HttpStatus.BAD_REQUEST);
			} else if (itemsviaje.getFecha().compareTo(viaje.get().getFechafin()) > 0 || itemsviaje.getFecha().compareTo(viaje.get().getFechainicio()) < 0) {
				return generateResponse("La fecha proporcionada no se encuentra entre las fechas del viaje.", HttpStatus.BAD_REQUEST);
			} else if (itemsviaje.getDescripcion().length() > 100) {
				return generateResponse("La descripción es demasiado larga.", HttpStatus.BAD_REQUEST);
			} else if (itemsviaje.getNombre().length() > 45) {
				return generateResponse("El nombre es demasiado larga.", HttpStatus.BAD_REQUEST);
			}

			itemsviajeDAO.save(itemsviaje);
			return generateResponse("El item se ha creado correctamente", HttpStatus.OK, viaje);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/items/{id}")
	public ResponseEntity<Object> deleteItem(@PathVariable int id) {
		try {
			if (!itemsviajeDAO.existsById(id)) {
				return generateResponse("El item no existe.", HttpStatus.NOT_FOUND);
			}

			itemsviajeDAO.deleteById(id);
			return generateResponse("El item se ha eliminado correctamente.", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/items")
	public ResponseEntity<Object> updateItem(ItemsViaje itemsviaje) {
		try {
			Optional<Viajes> viaje = viajesDAO.findById(itemsviaje.getId());

			if (!itemsviajeDAO.existsById(itemsviaje.getId())) {
				return generateResponse("El item no existe.", HttpStatus.NOT_FOUND);
			} else if (itemsviaje.getFecha().compareTo(viaje.get().getFechafin()) > 0 || itemsviaje.getFecha().compareTo(viaje.get().getFechainicio()) < 0) {
				return generateResponse("La fecha proporcionada no se encuentra entre las fechas del viaje.", HttpStatus.BAD_REQUEST);
			} else if (itemsviaje.getIdviajes() != itemsviajeDAO.findById(itemsviaje.getId()).get().getIdviajes()) {
				return generateResponse("El item no puede ser cambiado de viaje.", HttpStatus.BAD_REQUEST);
			}

			itemsviajeDAO.save(itemsviaje);
			return generateResponse("Datos del item modificados correctamente.", HttpStatus.OK, itemsviaje);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("status", status.value());

		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}

	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("status", status.value());
		map.put("data", responseObj);

		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}
}
