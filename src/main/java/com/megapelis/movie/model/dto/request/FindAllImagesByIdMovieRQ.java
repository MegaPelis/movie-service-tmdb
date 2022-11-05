package com.megapelis.movie.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase {@link FindAllImagesByIdMovieRQ}
 * @author sergio.barrios.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindAllImagesByIdMovieRQ {
    private String idMovie;
}
