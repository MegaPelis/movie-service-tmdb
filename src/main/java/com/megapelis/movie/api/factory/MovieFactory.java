package com.megapelis.movie.api.factory;

import com.megapelis.movie.api.handler.MovieHandler;
import com.megapelis.movie.api.handler.impl.*;
import com.megapelis.movie.model.dto.request.generic.Request;
import com.megapelis.movie.model.dto.response.Response;
import com.megapelis.movie.model.enums.MovieOperationEnum;
import com.megapelis.movie.model.enums.MovieStatusEnum;
import com.megapelis.movie.util.MovieCommon;
import com.megapelis.movie.util.MovieException;

/**
 * Clase {@link MovieFactory}
 * @author sergio.barrios.
 */
public class MovieFactory {

    private MovieFactory(){
    }

    /**
     * Fabrica que permite ejecutar mediante la operación.
     * @param request
     * @return {@link Response}
     */
    public static Response handler(Request request) {
        MovieCommon.output(request);
        Response response = null;
        MovieHandler handler = null;
        try {
            MovieCommon.isValidRequest(request);
            switch (MovieOperationEnum.isValid(request.getOperation())){
                case FIND_BY_ID:
                    handler = new FindByIdMovieHandler();
                    break;
                case FIND_ALL_IMAGES_BY_ID:
                    handler = new FindAllImagesByIdMovieHandler();
                    break;
                case FIND_ALL_VIDEO_BY_ID:
                    handler = new FindAllVideoByIdMovieHandler();
                    break;
                case FIND_ALL_BY_POPULAR:
                    handler = new FindAllByPopularMovieHandler();
                    break;
                default:
                    response =  MovieCommon.buildResponse(request, MovieStatusEnum.ERROR);
                    break;
            }
        } catch (MovieException e) {
            response =  MovieCommon.buildResponse(request, e.getStatus());
        }
        if(null == response && null != handler)
            response = handler.execute(request);
        MovieCommon.output(response);
        return response;
    }
}
