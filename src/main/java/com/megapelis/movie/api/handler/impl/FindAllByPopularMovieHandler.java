package com.megapelis.movie.api.handler.impl;

import com.megapelis.movie.api.handler.MovieHandler;
import com.megapelis.movie.model.dto.request.FindAllByPopularMovieRQ;
import com.megapelis.movie.model.dto.request.generic.Request;
import com.megapelis.movie.util.MovieCommon;
import com.megapelis.movie.util.MovieConstant;
import com.megapelis.movie.util.MovieException;

/**
 * Clase {@link FindAllByPopularMovieHandler}
 * @author sergio.barrios.
 */
public class FindAllByPopularMovieHandler extends MovieHandler {

    /**
     * Metodo que permite validar el payload.
     * @param request
     * @return {@link Object}
     * @throws MovieException
     */
    @Override
    public Object validatePayload(Request request) throws MovieException {
        FindAllByPopularMovieRQ findAllByPopularMovieRQ = convertPayload(request, FindAllByPopularMovieRQ.class);
        if(MovieConstant.INTEGER_ONE > findAllByPopularMovieRQ.getPage())
            findAllByPopularMovieRQ.setPage(MovieConstant.INTEGER_ONE);
        return findAllByPopularMovieRQ;
    }

    /**
     * Metodo que permite construir la url del servicio.
     * @param object
     * @return {@link String}
     * @throws MovieException
     */
    @Override
    public String url(Object object) throws MovieException {
        FindAllByPopularMovieRQ findAllByPopularMovieRQ = (FindAllByPopularMovieRQ) object;
        tmdbUrl.setOperation(MovieCommon.getEnv(MovieConstant.STRING_ENV_MEGAPELIS_TMDB_HOST_MOVIE_POPULAR));
        MovieCommon.addProperties(tmdbUrl, tmdbUrl.getHost(), tmdbUrl.getKey(), String.valueOf(findAllByPopularMovieRQ.getPage()));
        return buildUrl(tmdbUrl);
    }
}
