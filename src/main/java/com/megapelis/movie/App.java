package com.megapelis.movie;

import com.megapelis.movie.api.factory.MovieFactory;
import com.megapelis.movie.model.dto.request.generic.Request;
import com.megapelis.movie.model.dto.response.Response;

/**
 * Clase {@link App}
 * @author sergio.barrios.
 */
public class App{

    /**
     * Metodo que permite realizar el llamado de los servicios.
     * @param request
     * @return {@link Response}
     */
    public Response handler(Request request){
        return MovieFactory.handler(request);
    }
}
