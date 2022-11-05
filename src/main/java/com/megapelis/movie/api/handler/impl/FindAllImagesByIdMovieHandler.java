package com.megapelis.movie.api.handler.impl;

import com.megapelis.movie.api.handler.MovieHandler;
import com.megapelis.movie.model.dto.request.FindAllImagesByIdMovieRQ;
import com.megapelis.movie.model.dto.request.generic.Request;
import com.megapelis.movie.model.enums.MovieStatusEnum;
import com.megapelis.movie.util.MovieCommon;
import com.megapelis.movie.util.MovieConstant;
import com.megapelis.movie.util.MovieException;

/**
 * Clase {@link FindAllImagesByIdMovieHandler}
 * @author sergio.barrios.
 */
public class FindAllImagesByIdMovieHandler extends MovieHandler {

    /**
     * Metodo que permite validar el payload.
     * @param request
     * @return {@link Object}
     * @throws MovieException
     */
    @Override
    public Object validatePayload(Request request) throws MovieException {
        FindAllImagesByIdMovieRQ findByIdAndSeasonMovieRQ = convertPayload(request, FindAllImagesByIdMovieRQ.class);
        if(!MovieCommon.isValidString(findByIdAndSeasonMovieRQ.getIdMovie()))
            throw new MovieException(MovieStatusEnum.ERROR_FORMAT_REQUEST);
        return findByIdAndSeasonMovieRQ;
    }

    /**
     * Metodo que permite construir la url del servicio.
     * @param object
     * @return {@link String}
     * @throws MovieException
     */
    @Override
    public String url(Object object) throws MovieException {
        FindAllImagesByIdMovieRQ findByIdAndSeasonMovieRQ = (FindAllImagesByIdMovieRQ) object;
        tmdbUrl.setOperation(MovieCommon.getEnv(MovieConstant.STRING_ENV_MEGAPELIS_TMDB_HOST_MOVIE_BY_ID_ALL_IMAGES_NAME));
        MovieCommon.addProperties(tmdbUrl, tmdbUrl.getHost(), findByIdAndSeasonMovieRQ.getIdMovie(), tmdbUrl.getKey());
        return buildUrl(tmdbUrl);
    }
}
