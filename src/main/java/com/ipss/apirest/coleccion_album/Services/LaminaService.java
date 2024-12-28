package com.ipss.apirest.coleccion_album.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipss.apirest.coleccion_album.Models.Album;
import com.ipss.apirest.coleccion_album.Models.Lamina;
import com.ipss.apirest.coleccion_album.Repositories.LaminaRepository;

@Service
public class LaminaService {

  @Autowired
  private LaminaRepository laminaRepository;

  @Autowired
  private AlbumService albumService;

  public List<Lamina> findAll() {
    return laminaRepository.findAll();
  }

  public Lamina save(Lamina lamina) {
    // Verificar si existe el álbum
    if (lamina.getAlbum() != null && lamina.getAlbum().getId() != null) {
      Album album = albumService.findById(lamina.getAlbum().getId());
      if (album == null) {
        throw new RuntimeException("El álbum con ID " + lamina.getAlbum().getId() + " no existe");
      }
      lamina.setAlbum(album);
    }
    return laminaRepository.save(lamina);
  }

  public Lamina findById(Long id) {
    return laminaRepository.findById(id).orElse(null);
  }

  public List<Lamina> findByFaltante(boolean faltante) {
    return laminaRepository.findByFaltante(faltante);
  }

  public List<Lamina> findByWithRepetidas() {
    return laminaRepository.findByCantidadRepetidasGreaterThan(0);
  }

  public List<Lamina> saveAll(List<Lamina> laminas) {
    return laminaRepository.saveAll(laminas);
  }

}
