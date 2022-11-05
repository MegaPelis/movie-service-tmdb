package com.megapelis.movie.util;

import com.google.gson.Gson;
import com.megapelis.movie.model.dto.request.generic.Request;
import com.megapelis.movie.model.dto.response.Response;
import com.megapelis.movie.model.dto.response.ResponseStatus;
import com.megapelis.movie.model.entity.TMDB;
import com.megapelis.movie.model.entity.TMDBUrl;
import com.megapelis.movie.model.enums.MovieStatusEnum;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase {@link MovieCommon}
 * @author sergio.barrios.
 */
public class MovieCommon {

    private MovieCommon(){}

    /**
     * Metodo que permite validar una cadena no este vacia.
     * @param value
     * @return {@link boolean}
     */
    public static boolean isValidString(String value){
        return null != value && !value.trim().isEmpty();
    }

    /**
     * Metodo que permite validar varias cadenas.
     * @param values
     * @return {@link boolean}
     */
    public static boolean isValidString(String ... values) {
        for (String value : values) {
            if (!isValidString(value)) {
                return MovieConstant.BOOLEAN_FALSE;
            }
        }
        return MovieConstant.BOOLEAN_TRUE;
    }

    /**
     * Metodo que valida la petición.
     * @param request
     * @throws MovieException
     */
    public static void isValidRequest(Request request) throws MovieException {
        boolean isError = MovieConstant.BOOLEAN_FALSE;
        if(null == request || !isValidString(request.getTraceId(), request.getDateTime(), request.getService(), request.getOperation()))
            isError = MovieConstant.BOOLEAN_TRUE;
        if(!request.getService().equalsIgnoreCase(MovieConstant.STRING_SERVICE_NAME))
            isError = MovieConstant.BOOLEAN_TRUE;
        if(request.getService().equalsIgnoreCase(request.getOperation()))
            isError = MovieConstant.BOOLEAN_TRUE;
        if(isError)
            throw new MovieException(MovieStatusEnum.ERROR_FORMAT_REQUEST);
    }

    /**
     * Metodo que permite obtener la fecha y hora actual.
     * @return {@link String}
     */
    public static String getDateTime(){
        return ZonedDateTime.now(ZoneId.of(MovieConstant.STRING_DATE_ZONE))
                .format(DateTimeFormatter.ofPattern(MovieConstant.STRING_DATE_TIME_FORMAT));
    }

    /**
     * Metodo que permite obtener el valor de una variable de entorno.
     * @param name
     * @return {@link String}
     * @throws MovieException
     */
    public static String getEnv(String name) throws MovieException {
        if(!isValidString(name))
            throw new MovieException(MovieStatusEnum.ERROR_ENV);
        String env = System.getenv(name);
        if(!isValidString(env)){
            throw new MovieException(MovieStatusEnum.ERROR_ENV_NOT_CONTENT);
        }
        return env;
    }

    /**
     * Metodo que permite obtener el objeto en cadena.
     * @param object
     * @return {@link  String}
     */
    public static String getStringJSON(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    /**
     * Metodo que convierte un objeto a clase.
     * @param object
     * @param clazz
     * @return {@link T}
     * @param <T>
     */
    public static <T> T convertObjectToClass(Object object, Class<T> clazz){
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return gson.fromJson(json, clazz);
    }

    /**
     * Metodo que permite darle formato a una cadena
     * @param value
     * @param values
     * @return {@link String}
     */
    public static String formatString(String value, String ... values){
        return String.format(value, values);
    }

    /**
     * Metodo que permite agregar propiedades a la url tmdb.
     * @param tmdbUrl
     * @param properties
     * @return {@link String[]}
     */
    public static String [] addProperties(TMDBUrl tmdbUrl, String ... properties){
        List<String> propertiesList;
        if(null == tmdbUrl.getProperties())
            propertiesList = new ArrayList<>();
        else
            propertiesList = Arrays.stream(properties).collect(Collectors.toList());
        Arrays.stream(properties).forEach(propertiesList::add);
        tmdbUrl.setProperties(propertiesList.stream().toArray(String[]::new));
        return tmdbUrl.getProperties();
    }

    /**
     * Metodo que permite registrar la salida para cloud watch.
     * @param object
     */
    public static void output(Object object){
        if(object instanceof Response || object instanceof Request || object instanceof TMDB)
            System.out.println(getStringJSON(object));
        else
            System.out.println(object);
    }

    /**
     * Metodo que permite construir la respuesta del servicio.
     * @param request
     * @return {@link Response}
     */
    public static Response buildResponse(Request request){
        return buildResponse(request, null, null, null, null);
    }

    /**
     * Metodo que permite construir la respuesta del servicio.
     * @param request
     * @param statusEnum
     * @return {@link Response}
     */
    public static Response buildResponse(Request request, MovieStatusEnum statusEnum){
        return buildResponse(request, statusEnum, null);
    }

    /**
     * Metodo que permite construir la respuesta del servicio.
     * @param request
     * @param statusEnum
     * @param data
     * @return {@link Response}
     */
    public static Response buildResponse(Request request, MovieStatusEnum statusEnum, Object data){
        return buildResponse(request, statusEnum.getCode(), statusEnum.getMessageBackend(), statusEnum.getMessageFrontend(), data);
    }

    /**
     * Metodo que permite construir la respuesta del servicio.
     * @param request
     * @param code
     * @param messageBackend
     * @param messageFrontend
     * @param data
     * @return {@link Response}
     */
    private static Response buildResponse(Request request, String code, String messageBackend, String messageFrontend, Object data){
        if(null == request)
            return null;
        if(!isValidString(code,messageBackend, messageFrontend)){
            code = MovieStatusEnum.ERROR.getCode();
            messageBackend = MovieStatusEnum.ERROR.getMessageBackend();
            messageFrontend = MovieStatusEnum.ERROR.getMessageFrontend();
        }
        if(null != data){
            Gson gson = new Gson();
            data = gson.toJson(data);
        }
        String dateTime = MovieCommon.getDateTime();
        return Response
                .builder()
                .traceId(request.getTraceId())
                .dateTime(dateTime)
                .service(request.getService())
                .operation(request.getOperation())
                .status(new ResponseStatus(code, messageBackend, messageFrontend))
                .data(data)
                .build();
    }
}
