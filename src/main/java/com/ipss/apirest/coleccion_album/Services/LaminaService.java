package com.ipss.apirest.coleccion_album.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipss.apirest.coleccion_album.Models.Lamina;
import com.ipss.apirest.coleccion_album.Repositories.LaminaRepository;

@Service
public class LaminaService {

  @Autowired
  private LaminaRepository laminaRepository;

  public List<Lamina> findAll() {
    return laminaRepository.findAll();
  }

  public Lamina save(Lamina lamina) {
    return laminaRepository.save(lamina);
  }

  public Lamina findById(Long id) {
    return laminaRepository.findById(id).orElse(null);
  }

  public List<Lamina> findByFaltante(boolean faltante) {
    return laminaRepository.findByFaltante(faltante);
  }

  public List<Lamina> findByWithRepetidas() {
    return laminaRepository.findByCantidadRepetidasGreaterThan(0);
  }

  public List<Lamina> saveAll(List<Lamina> laminas) {
    return laminaRepository.saveAll(laminas);
  }

}
