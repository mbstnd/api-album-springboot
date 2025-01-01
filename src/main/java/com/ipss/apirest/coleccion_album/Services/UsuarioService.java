package com.ipss.apirest.coleccion_album.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipss.apirest.coleccion_album.Models.Usuario;
import com.ipss.apirest.coleccion_album.Repositories.UsuarioRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Transactional(readOnly = true)
  public List<Usuario> findAll() {
    return usuarioRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Usuario findById(Long id) {
    return usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
  }

  @Transactional
  public Usuario save(Usuario usuario) {
    if (usuarioRepository.existsByFirstName(usuario.getFirstName())) {
      throw new RuntimeException("El nombre ya está registrado");
    }
    if (usuarioRepository.existsByLastName(usuario.getLastName())) {
      throw new RuntimeException("El apellido ya está registrado");
    }
    if (usuarioRepository.existsByEmail(usuario.getEmail())) {
      throw new RuntimeException("El email ya está registrado");
    }
    return usuarioRepository.save(usuario);
  }

  @Transactional
  public Usuario update(Long id, Usuario usuario) {
    Usuario existingUsuario = findById(id);
    existingUsuario.setFirstName(usuario.getFirstName());
    existingUsuario.setLastName(usuario.getLastName());
    existingUsuario.setEmail(usuario.getEmail());
    return usuarioRepository.save(existingUsuario);
  }

  @Transactional
  public void delete(Long id) {
    if (!usuarioRepository.existsById(id)) {
      throw new RuntimeException("Usuario no encontrado con ID: " + id);
    }
    usuarioRepository.deleteById(id);
  }
}
