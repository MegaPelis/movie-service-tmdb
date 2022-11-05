package com.megapelis.movie.util;

/**
 * Clase {@link MovieConstant}
 * @author sergio.barrios.
 */
public class MovieConstant {

    public static final boolean BOOLEAN_TRUE = true;
    public static final boolean BOOLEAN_FALSE = false;

    public static final int INTEGER_ONE = 1;
    public static final String STRING_COMMON_UTF8 = "UTF-8";

    // Fecha
    public static final String STRING_DATE_ZONE = "America/Bogota";
    public static final String STRING_DATE_TIME_FORMAT = "yyyy/MM/dd hh:mm:ss";

    // Variables Entorno
    public static final String STRING_ENV_MEGAPELIS_TMDB_HOST_NAME = "MEGAPELIS_TMDB_HOST";
    public static final String STRING_ENV_MEGAPELIS_TMDB_HOST_MOVIE_BY_ID_NAME = "MEGAPELIS_TMDB_HOST_MOVIE_BY_ID";
    public static final String STRING_ENV_MEGAPELIS_TMDB_HOST_MOVIE_BY_ID_ALL_IMAGES = "MEGAPELIS_TMDB_HOST_MOVIE_BY_ID_ALL_IMAGES";
    public static final String STRING_ENV_MEGAPELIS_TMDB_HOST_MOVIE_BY_ID_ALL_VIDEO = "MEGAPELIS_TMDB_HOST_MOVIE_BY_ID_ALL_VIDEO";
    public static final String STRING_ENV_MEGAPELIS_TMDB_HOST_MOVIE_POPULAR = "MEGAPELIS_TMDB_HOST_MOVIE_POPULAR";
    public static final String STRING_ENV_MEGAPELIS_TMDB_KEY_NAME = "MEGAPELIS_TMDB_KEY";

    // SERVICIO
    public static final String STRING_SERVICE_NAME = "SerieTMDB";

    // TMDB
    public static final boolean BOOLEAN_TMDB_OUTPUT = true;
    public static final String STRING_TMDB_METHOD_GET = "GET";
    public static final String STRING_TMDB_METHOD_POST = "POST";
    public static final String STRING_TMDB_PROPERTY_CONTENT_TYPE_NAME = "Content-Type";
    public static final String STRING_TMDB_PROPERTY_CONTENT_TYPE_VALUE = "application/json";

    private MovieConstant(){}

}
