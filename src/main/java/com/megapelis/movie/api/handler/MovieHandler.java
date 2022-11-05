package com.megapelis.movie.api.handler;

import com.google.gson.JsonObject;
import com.megapelis.movie.model.dto.request.generic.Request;
import com.megapelis.movie.model.dto.response.Response;
import com.megapelis.movie.model.entity.TMDB;
import com.megapelis.movie.model.enums.MovieStatusEnum;
import com.megapelis.movie.util.MovieCommon;
import com.megapelis.movie.util.MovieException;

/**
 * Clase {@link MovieHandler}
 * @author sergio.barrios.
 */
public abstract class MovieHandler extends MovieGenericCommon {

    /**
     * Metodo que permite ejecutar la logica del handler.
     * @param request
     * @return {@link Response}
     */
    public Response execute(Request request){
        Response response;
        try {
            Object object = validatePayload(request);
            this.tmdbUrl = buildTMBDUrl();
            String url = url(object);
            TMDB tmdb = tmdb(url);
            JsonObject jsonObject = this.tmdb.execute(tmdb);
            response = MovieCommon.buildResponse(request, MovieStatusEnum.SUCCESS, jsonObject);
        } catch (MovieException exception) {
            response = MovieCommon.buildResponse(request, exception.getStatus());
        }
        return response;
    }

    /**
     * Metodo que permite validar el payload.
     * @param request
     * @return {@link Object}
     * @throws MovieException
     */
    public abstract Object validatePayload(Request request) throws MovieException;

    /**
     * Metodo que permite construir la url del servicio.
     * @param object
     * @return {@link String}
     * @throws MovieException
     */
    public abstract String url(Object object) throws MovieException;
}
