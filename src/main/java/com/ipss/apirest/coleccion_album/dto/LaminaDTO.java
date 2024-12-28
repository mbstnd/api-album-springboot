package com.ipss.apirest.coleccion_album.dto;

import com.ipss.apirest.coleccion_album.Models.Lamina;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class LaminaDTO {
  private Long id;
  private String nombre;
  private String imagen;
  private int cantidadRepetidas;
  private boolean faltante;
  private String albumNombre;
  private Long albumId;

  public LaminaDTO(Lamina lamina) {
    this.id = lamina.getId();
    this.nombre = lamina.getNombre();
    this.imagen = lamina.getImagen();
    this.cantidadRepetidas = lamina.getCantidadRepetidas();
    this.faltante = lamina.isFaltante();
    if (lamina.getAlbum() != null) {
      this.albumNombre = lamina.getAlbum().getNombre();
      this.albumId = lamina.getAlbum().getId();
    }
  }

  public static List<LaminaDTO> fromLaminas(List<Lamina> laminas) {
    return laminas.stream()
        .map(LaminaDTO::new)
        .collect(Collectors.toList());
  }
}
