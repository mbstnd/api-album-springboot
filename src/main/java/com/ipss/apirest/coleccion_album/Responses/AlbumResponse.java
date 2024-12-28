package com.ipss.apirest.coleccion_album.Responses;

import com.ipss.apirest.coleccion_album.Models.Album;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AlbumResponse extends ApiResponse<Album> {
  public AlbumResponse(int status, String message, Album data) {
    super(status, message, data);
  }

}
