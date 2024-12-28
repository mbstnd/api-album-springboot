package com.ipss.apirest.coleccion_album.Responses;

import java.util.List;
import com.ipss.apirest.coleccion_album.Models.Lamina;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LaminaResponse extends ApiResponse<Object> {

  public <T> LaminaResponse(int status, String message, List<T> data) {
    super(status, message, data);
  }

  public LaminaResponse(int status, String message, Lamina lamina) {
    super(status, message, List.of(lamina));
  }
}