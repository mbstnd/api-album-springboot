package com.ipss.apirest.coleccion_album.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipss.apirest.coleccion_album.Models.Album;
import com.ipss.apirest.coleccion_album.Repositories.AlbumRepository;

@Service
public class AlbumService {

  @Autowired
  private AlbumRepository albumRepository;

  public List<Album> findAll() {
    return albumRepository.findAll();
  }

  public Album save(Album album) {
    return albumRepository.save(album);
  }

  public Album findById(Long id) {
    return albumRepository.findById(id).orElse(null);
  }

  public void deleteById(Long id) {
    albumRepository.deleteById(id);
  }

}
