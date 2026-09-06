package com.daily_notes.notes.mapper;

import tools.jackson.databind.ObjectMapper;

public class CustomMapper {
    private final static ObjectMapper mapper = new ObjectMapper();

    public static <T> T mapToEntity(Object source, Class<T> targetType) {
        return mapper.convertValue(source, targetType);
    }

}
