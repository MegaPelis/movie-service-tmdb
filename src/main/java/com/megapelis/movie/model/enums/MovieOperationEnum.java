package com.megapelis.movie.model.enums;

import com.megapelis.movie.util.MovieException;
import lombok.Getter;

import java.util.Arrays;

/**
 * Clase {@link MovieOperationEnum}
 * @author sergio.barrios.
 */
@Getter
public enum MovieOperationEnum {
    FIND_BY_ID("findById"),
    FIND_ALL_IMAGES_BY_ID("findAllImagesById"),
    FIND_ALL_VIDEO_BY_ID("findAllVideoById"),
    FIND_ALL_BY_POPULAR("findAllByPopular");

    private String name;

    MovieOperationEnum(String name){
        this.name = name;
    }

    /**
     * Metodo que permite validar si existe esa operación
     * @param name
     * @return {@link MovieOperationEnum}
     * @throws MovieException
     */
    public static MovieOperationEnum isValid(String name) throws MovieException {
        return Arrays.stream(MovieOperationEnum.values())
                .filter(operationEnum -> operationEnum.getName().equalsIgnoreCase(name))
                .findFirst().orElseThrow(() -> new MovieException(MovieStatusEnum.ERROR_SERVICE_OR_OPERATION));
    }
}
