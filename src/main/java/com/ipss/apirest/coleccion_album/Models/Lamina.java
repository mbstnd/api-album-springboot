package com.ipss.apirest.coleccion_album.Models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "laminas")
public class Lamina {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nombre;

  private String imagen;

  @Column(name = "cantidad_repetidas")
  private int cantidadRepetidas = 0;

  private boolean faltante = true;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "album_id", nullable = false)
  @JsonBackReference
  private Album album;

  @Column(name = "fecha_creacion")
  private LocalDateTime fechaCreacion;

  @PrePersist
  protected void onCreate() {
    fechaCreacion = LocalDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getImagen() {
    return imagen;
  }

  public void setImagen(String imagen) {
    this.imagen = imagen;
  }

  public int getCantidadRepetidas() {
    return cantidadRepetidas;
  }

  public void setCantidadRepetidas(int cantidadRepetidas) {
    this.cantidadRepetidas = cantidadRepetidas;
  }

  public boolean isFaltante() {
    return faltante;
  }

  public void setFaltante(boolean faltante) {
    this.faltante = faltante;
  }

  public Album getAlbum() {
    return album;
  }

  public void setAlbum(Album album) {
    this.album = album;
  }

}
