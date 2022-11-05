package com.megapelis.movie.api.handler;

import com.megapelis.movie.api.tmdb.ITMDBConnection;
import com.megapelis.movie.api.tmdb.impl.TMDBConnection;
import com.megapelis.movie.model.dto.request.generic.Request;
import com.megapelis.movie.model.dto.request.generic.RequestProperty;
import com.megapelis.movie.model.entity.TMDB;
import com.megapelis.movie.model.entity.TMDBUrl;
import com.megapelis.movie.model.enums.MovieStatusEnum;
import com.megapelis.movie.util.MovieCommon;
import com.megapelis.movie.util.MovieConstant;
import com.megapelis.movie.util.MovieException;

/**
 * Clase {@link MovieGenericCommon}
 * @author sergio.barrios.
 */
public class MovieGenericCommon {
    protected ITMDBConnection tmdb;
    protected String method;
    protected TMDBUrl tmdbUrl;

    public MovieGenericCommon(){
        this.tmdb = new TMDBConnection();
        this.method = MovieConstant.STRING_TMDB_METHOD_GET;
    }

    /**
     * Metodo que permite parsear el payload.
     * @param request
     * @param clazz
     * @return {@link T}
     * @param <T>
     * @throws MovieException
     */
    protected <T> T convertPayload(Request request, Class<T> clazz) throws MovieException {
        if(null == request || null == request.getData())
            throw new MovieException(MovieStatusEnum.ERROR_FORMAT_REQUEST);
        return MovieCommon.convertObjectToClass(request.getData(), clazz);
    }

    /**
     * Metodo que permite construir el objeto de tmdb url.
     * @return {@link TMDBUrl}
     * @throws MovieException
     */
    protected TMDBUrl buildTMBDUrl() throws MovieException {
        String host = MovieCommon.getEnv(MovieConstant.STRING_ENV_MEGAPELIS_TMDB_HOST_NAME);
        String key = MovieCommon.getEnv(MovieConstant.STRING_ENV_MEGAPELIS_TMDB_KEY_NAME);
        return TMDBUrl.builder()
                .host(host)
                .key(key)
                .method(method)
                .build();
    }

    /**
     * Metodo que permite construir la url de tmdb con los valores reemplazados.
     * @param tmdbUrl
     * @return {@link String}
     */
    protected String buildUrl(TMDBUrl tmdbUrl){
        return MovieCommon.formatString(tmdbUrl.getOperation(), tmdbUrl.getProperties());
    }

    /**
     * Metodo que permite construir el objeto de TMDB.
     * @param url
     * @return {@link TMDB}
     */
    protected TMDB tmdb(String url){
        TMDB tmdb = TMDB
                .builder()
                .url(url)
                .output(MovieConstant.BOOLEAN_TMDB_OUTPUT)
                .method(method)
                .property(new RequestProperty(MovieConstant.STRING_TMDB_PROPERTY_CONTENT_TYPE_NAME, MovieConstant.STRING_TMDB_PROPERTY_CONTENT_TYPE_VALUE))
                .build();
        MovieCommon.output(tmdb);
        return tmdb;
    }
}
