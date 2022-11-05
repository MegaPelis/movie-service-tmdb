package com.megapelis.movie.api.tmdb;

import com.google.gson.JsonObject;
import com.megapelis.movie.model.entity.TMDB;
import com.megapelis.movie.util.MovieException;

/**
 * Clase {@link ITMDBConnection}
 * @author sergio.barrios.
 */
public interface ITMDBConnection {

    /**
     * Metodo que permite realizar la consulta TMDB.
     * @param tmdb
     * @return {@link JsonObject}
     * @throws MovieException
     */
    JsonObject execute(TMDB tmdb) throws MovieException;
}
