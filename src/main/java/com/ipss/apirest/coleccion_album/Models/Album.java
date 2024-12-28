package com.ipss.apirest.coleccion_album.Models;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "albums")
public class Album {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String nombre;

  private String imagen;

  @Column(name = "fecha_lanzamiento")
  private LocalDate fechaLanzamiento;

  @Column(name = "tipo_laminas")
  private String tipoLaminas;

  @JsonManagedReference // Evita recursión infinita
  @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Lamina> laminas;

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

  public LocalDate getFechaLanzamiento() {
    return fechaLanzamiento;
  }

  public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
    this.fechaLanzamiento = fechaLanzamiento;
  }

  public String getTipoLaminas() {
    return tipoLaminas;
  }

  public void setTipoLaminas(String tipoLaminas) {
    this.tipoLaminas = tipoLaminas;
  }

  public List<Lamina> getLaminas() {
    return laminas;
  }

  public void setLaminas(List<Lamina> laminas) {
    this.laminas = laminas;
  }

}
