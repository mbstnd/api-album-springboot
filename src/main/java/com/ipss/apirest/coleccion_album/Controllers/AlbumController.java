package com.ipss.apirest.coleccion_album.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipss.apirest.coleccion_album.Models.Album;
import com.ipss.apirest.coleccion_album.Responses.AlbumResponse;
import com.ipss.apirest.coleccion_album.Responses.ApiResponse;
import com.ipss.apirest.coleccion_album.Services.AlbumService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/v1/album")
public class AlbumController {
  @Autowired
  private AlbumService albumService;

  @GetMapping("/getAll")
  public ResponseEntity<ApiResponse<List<Album>>> getAllAlbums() {
    return ResponseEntity.ok(
        new ApiResponse<>(200, "Álbumes encontrados", albumService.findAll()));
  }

  @PostMapping("/create")
  public ResponseEntity<AlbumResponse> createAlbum(@RequestBody Album album) {
    try {
      // Establecer relación bidireccional
      if (album.getLaminas() != null) {
        album.getLaminas().forEach(lamina -> lamina.setAlbum(album));
      }

      Album savedAlbum = albumService.save(album);

      String mensaje = (album.getLaminas() != null && !album.getLaminas().isEmpty())
          ? String.format("Álbum creado exitosamente con %d láminas", album.getLaminas().size())
          : "Álbum creado exitosamente";

      return ResponseEntity.status(HttpStatus.CREATED)
          .body(new AlbumResponse(201, mensaje, savedAlbum));

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new AlbumResponse(500, "Error al crear el álbum: " + e.getMessage(), null));
    }
  }

  @GetMapping("getById/{id}")
  public ResponseEntity<AlbumResponse> getAlbumById(@PathVariable Long id) {
    Album album = albumService.findById(id);
    return album != null
        ? ResponseEntity.ok(new AlbumResponse(200, "Álbum encontrado", album))
        : ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new AlbumResponse(404, "Álbum no encontrado", null));
  }

  @PutMapping("update/{id}")
  public ResponseEntity<AlbumResponse> updateAlbum(@PathVariable Long id, @RequestBody Album album) {
    try {
      Album existingAlbum = albumService.findById(id);
      if (existingAlbum == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new AlbumResponse(404, "Álbum no encontrado", null));
      }
      album.setId(id);
      Album updatedAlbum = albumService.save(album);
      return ResponseEntity.ok(new AlbumResponse(200, "Álbum actualizado exitosamente", updatedAlbum));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new AlbumResponse(500, "Error al actualizar el álbum", null));
    }
  }

  @DeleteMapping("delete/{id}")
  public ResponseEntity<AlbumResponse> deleteAlbum(@PathVariable Long id) {
    try {
      Album albumToDelete = albumService.findById(id);
      if (albumToDelete == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new AlbumResponse(404, "Álbum no encontrado", null));
      }

      // Guardar datos del álbum antes de eliminar
      Album deletedAlbum = new Album();
      deletedAlbum.setId(albumToDelete.getId());
      deletedAlbum.setNombre(albumToDelete.getNombre());
      deletedAlbum.setImagen(albumToDelete.getImagen());
      deletedAlbum.setFechaLanzamiento(albumToDelete.getFechaLanzamiento());
      deletedAlbum.setTipoLaminas(albumToDelete.getTipoLaminas());

      // Eliminar el álbum
      albumService.deleteById(id);

      return ResponseEntity.ok(new AlbumResponse(200, "Álbum eliminado exitosamente", deletedAlbum));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new AlbumResponse(500, "Error al eliminar el álbum", null));
    }
  }
}
