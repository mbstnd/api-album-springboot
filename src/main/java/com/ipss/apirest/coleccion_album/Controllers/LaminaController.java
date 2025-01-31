package com.ipss.apirest.coleccion_album.Controllers;

import java.util.List;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipss.apirest.coleccion_album.Models.Lamina;
import com.ipss.apirest.coleccion_album.Responses.LaminaResponse;
import com.ipss.apirest.coleccion_album.Services.LaminaService;
import com.ipss.apirest.coleccion_album.dto.LaminaDTO;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/v1/laminas")
public class LaminaController {

  @Autowired
  private LaminaService laminaService;

  @GetMapping("/getAll")
  public ResponseEntity<LaminaResponse> getAllLaminas() {
    try {
      List<Lamina> laminas = laminaService.findAll();
      List<LaminaDTO> laminasDTO = LaminaDTO.fromLaminas(laminas);
      return ResponseEntity.ok(new LaminaResponse(200, "Láminas encontradas", laminasDTO));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new LaminaResponse(500, "Error al obtener las láminas", Collections.emptyList()));
    }
  }

  @GetMapping("/getById/{id}")
  public ResponseEntity<LaminaResponse> getLaminaById(@PathVariable Long id) {
    try {
      Lamina lamina = laminaService.findById(id);
      if (lamina != null) {
        return ResponseEntity.ok(new LaminaResponse(200, "Lámina encontrada", lamina));
      }
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new LaminaResponse(404, "Lámina no encontrada", List.of()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new LaminaResponse(500, "Error al buscar la lámina", Collections.emptyList()));
    }
  }

  @PostMapping("/create")
  public ResponseEntity<LaminaResponse> createLamina(@RequestBody Lamina lamina) {
    try {
      Lamina savedLamina = laminaService.save(lamina);
      return ResponseEntity.status(HttpStatus.CREATED)
          .body(new LaminaResponse(201, "Lámina creada exitosamente", savedLamina));
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new LaminaResponse(400, e.getMessage(), Collections.emptyList()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new LaminaResponse(500, "Error al crear la lámina", Collections.emptyList()));
    }
  }

  @GetMapping("/faltantes")
  public ResponseEntity<LaminaResponse> getLaminasFaltantes() {
    List<Lamina> faltantes = laminaService.findByFaltante(true);
    return ResponseEntity.ok(new LaminaResponse(200, "Láminas faltantes encontradas", faltantes));
  }

  @GetMapping("/repetidas")
  public ResponseEntity<LaminaResponse> getLaminasRepetidas() {
    List<Lamina> repetidas = laminaService.findByWithRepetidas();
    return ResponseEntity.ok(new LaminaResponse(200, "Láminas repetidas encontradas", repetidas));
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<LaminaResponse> updateLamina(@PathVariable Long id, @RequestBody Lamina lamina) {
    try {
      // Verificar si existe la lámina
      Lamina existingLamina = laminaService.findById(id);
      if (existingLamina == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new LaminaResponse(404, "Lámina no encontrada", Collections.emptyList()));
      }

      // Actualizar datos
      lamina.setId(id);
      lamina.setAlbum(existingLamina.getAlbum());

      // Guardar cambios
      Lamina updatedLamina = laminaService.save(lamina);
      return ResponseEntity.ok(new LaminaResponse(200, "Lámina actualizada exitosamente", updatedLamina));

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new LaminaResponse(500, "Error al actualizar la lámina", Collections.emptyList()));
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<LaminaResponse> deleteLamina(@PathVariable Long id) {
    try {
      // Verificar si existe la lámina
      Lamina laminaToDelete = laminaService.findById(id);
      if (laminaToDelete == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new LaminaResponse(404, "Lámina no encontrada", Collections.emptyList()));
      }

      // Eliminar lámina
      laminaService.deleteById(id);
      return ResponseEntity.ok(new LaminaResponse(200, "Lámina eliminada exitosamente", laminaToDelete));

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new LaminaResponse(500, "Error al eliminar la lámina", Collections.emptyList()));
    }
  }

}
