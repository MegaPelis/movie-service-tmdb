package com.megapelis.movie.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase {@link FindAllVideoByIdMovieRQ}
 * @author sergio.barrios.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindAllVideoByIdMovieRQ {
    private String idMovie;
}
