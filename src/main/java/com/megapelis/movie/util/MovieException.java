package com.megapelis.movie.util;

import com.megapelis.movie.model.enums.MovieStatusEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase {@link MovieException}
 * @author sergio.barrios.
 */
@Getter
@Setter
public class MovieException extends Exception{
    private MovieStatusEnum status;

    public MovieException(MovieStatusEnum status){
        super(status.getMessageBackend());
        this.status = status;
    }
}
