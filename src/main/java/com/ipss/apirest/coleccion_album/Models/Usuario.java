package com.ipss.apirest.coleccion_album.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "usuarios")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String firstName;

  @Column(nullable = false, unique = true)
  private String lastName;

  @Column(nullable = false)
  private String email;

  @Column(name = "fecha_creacion")
  private LocalDateTime fechaCreacion;

  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
  private List<Album> albums = new ArrayList<>();

  @PrePersist
  protected void onCreate() {
    fechaCreacion = LocalDateTime.now();
  }

}
