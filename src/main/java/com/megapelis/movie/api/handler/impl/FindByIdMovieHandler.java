package com.megapelis.movie.api.handler.impl;

import com.megapelis.movie.api.handler.MovieHandler;
import com.megapelis.movie.model.dto.request.FindByIdMovieRQ;
import com.megapelis.movie.model.dto.request.generic.Request;
import com.megapelis.movie.model.enums.MovieStatusEnum;
import com.megapelis.movie.util.MovieCommon;
import com.megapelis.movie.util.MovieConstant;
import com.megapelis.movie.util.MovieException;

/**
 * Clase {@link FindByIdMovieHandler}
 * @author sergio.barrios.
 */
public class FindByIdMovieHandler extends MovieHandler {

    /**
     * Metodo que permite validar el payload.
     * @param request
     * @return {@link Object}
     * @throws MovieException
     */
    @Override
    public Object validatePayload(Request request) throws MovieException {
        FindByIdMovieRQ findByIdMovieRQ = convertPayload(request, FindByIdMovieRQ.class);
        if(!MovieCommon.isValidString(findByIdMovieRQ.getIdMovie()))
            throw new MovieException(MovieStatusEnum.ERROR_FORMAT_REQUEST);
        return findByIdMovieRQ;
    }

    /**
     * Metodo que permite construir la url del servicio.
     * @param object
     * @return {@link String}
     * @throws MovieException
     */
    @Override
    public String url(Object object) throws MovieException {
        FindByIdMovieRQ findByIdMovieRQ = (FindByIdMovieRQ) object;
        tmdbUrl.setOperation(MovieCommon.getEnv(MovieConstant.STRING_ENV_MEGAPELIS_TMDB_HOST_MOVIE_BY_ID_NAME));
        MovieCommon.addProperties(tmdbUrl, tmdbUrl.getHost(), findByIdMovieRQ.getIdMovie(),  tmdbUrl.getKey());
        return buildUrl(tmdbUrl);
    }
}
