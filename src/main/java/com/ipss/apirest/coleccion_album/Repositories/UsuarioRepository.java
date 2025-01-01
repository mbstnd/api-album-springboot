package com.ipss.apirest.coleccion_album.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipss.apirest.coleccion_album.Models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  boolean existsByFirstName(String firstName);

  boolean existsByLastName(String lastName);

  boolean existsByEmail(String email);

}
