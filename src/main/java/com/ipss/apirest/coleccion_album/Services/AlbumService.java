package com.ipss.apirest.coleccion_album.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipss.apirest.coleccion_album.Models.Album;
import com.ipss.apirest.coleccion_album.Models.Usuario;
import com.ipss.apirest.coleccion_album.Repositories.AlbumRepository;

import jakarta.transaction.Transactional;

@Service
public class AlbumService {

  @Autowired
  private AlbumRepository albumRepository;

  @Autowired
  private UsuarioService usuarioService;

  public List<Album> findAll() {
    return albumRepository.findAll();
  }

  @Transactional
  public Album save(Album album) {
    if (album.getUsuario() != null && album.getUsuario().getId() != null) {
      Usuario usuario = usuarioService.findById(album.getUsuario().getId());
      album.setUsuario(usuario);
    }
    return albumRepository.save(album);
  }

  public Album findById(Long id) {
    return albumRepository.findById(id).orElse(null);
  }

  public void deleteById(Long id) {
    albumRepository.deleteById(id);
  }

}
