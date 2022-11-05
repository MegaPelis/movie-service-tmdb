package com.megapelis.movie.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Clase {@link FindAllByPopularMovieRQ}
 * @author sergio.barrios.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindAllByPopularMovieRQ implements Serializable {
    private int page;
}
