package com.ipss.apirest.coleccion_album.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipss.apirest.coleccion_album.Models.Lamina;

public interface LaminaRepository extends JpaRepository<Lamina, Long> {
  List<Lamina> findByAlbumId(Long albumId);

  List<Lamina> findByFaltante(boolean faltante);

  List<Lamina> findByCantidadRepetidasGreaterThan(int cantidad);

}
