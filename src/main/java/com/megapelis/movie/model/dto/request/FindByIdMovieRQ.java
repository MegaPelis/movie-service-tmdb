package com.megapelis.movie.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Clase {@link FindByIdMovieRQ}
 * @author sergio.barrios.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FindByIdMovieRQ implements Serializable {
    private String idMovie;
}
