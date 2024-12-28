package com.ipss.apirest.coleccion_album.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipss.apirest.coleccion_album.Models.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {
  boolean existsByNombre(String nombre);

}
