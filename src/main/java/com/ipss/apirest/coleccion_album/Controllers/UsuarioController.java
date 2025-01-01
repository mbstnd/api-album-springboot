package com.ipss.apirest.coleccion_album.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipss.apirest.coleccion_album.Models.Album;
import com.ipss.apirest.coleccion_album.Models.Usuario;
import com.ipss.apirest.coleccion_album.Responses.ApiResponse;
import com.ipss.apirest.coleccion_album.Services.UsuarioService;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {
  @Autowired
  private UsuarioService usuarioService;

  @GetMapping("/getAll")
  public ResponseEntity<ApiResponse<List<Usuario>>> getAllUsuarios() {
    try {
      List<Usuario> usuarios = usuarioService.findAll();
      return ResponseEntity.ok(new ApiResponse<>(200, "Usuarios encontrados", usuarios));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ApiResponse<>(500, e.getMessage(), null));
    }
  }

  @GetMapping("getById/{id}")
  public ResponseEntity<ApiResponse<Usuario>> getUsuarioById(@PathVariable Long id) {
    try {
      Usuario usuario = usuarioService.findById(id);
      return ResponseEntity.ok(new ApiResponse<>(200, "Usuario encontrado", usuario));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ApiResponse<>(404, e.getMessage(), null));
    }
  }

  @GetMapping("/{userId}/albums")
  public ResponseEntity<ApiResponse<List<Album>>> getAlbumsByUsuario(@PathVariable Long userId) {
    try {
      Usuario usuario = usuarioService.findById(userId);
      return ResponseEntity.ok(
          new ApiResponse<>(200,
              String.format("Álbumes del usuario %s %s",
                  usuario.getFirstName(),
                  usuario.getLastName()),
              usuario.getAlbums()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ApiResponse<>(404, e.getMessage(), null));
    }
  }

  @PostMapping("/create")
  public ResponseEntity<ApiResponse<Usuario>> createUsuario(@RequestBody Usuario usuario) {
    try {
      Usuario savedUsuario = usuarioService.save(usuario);
      return ResponseEntity.status(HttpStatus.CREATED)
          .body(new ApiResponse<>(201, "Usuario creado exitosamente", savedUsuario));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ApiResponse<>(400, e.getMessage(), null));
    }
  }

  @PutMapping("update/{id}")
  public ResponseEntity<ApiResponse<Usuario>> updateUsuario(
      @PathVariable Long id,
      @RequestBody Usuario usuario) {
    try {
      Usuario updatedUsuario = usuarioService.update(id, usuario);
      return ResponseEntity.ok(new ApiResponse<>(200, "Usuario actualizado exitosamente", updatedUsuario));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ApiResponse<>(400, e.getMessage(), null));
    }
  }

  @DeleteMapping("delete/{id}")
  public ResponseEntity<ApiResponse<Void>> deleteUsuario(@PathVariable Long id) {
    try {
      usuarioService.delete(id);
      return ResponseEntity.ok(new ApiResponse<>(200, "Usuario eliminado exitosamente", null));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ApiResponse<>(404, e.getMessage(), null));
    }
  }

}
