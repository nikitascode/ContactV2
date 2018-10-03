package com.nick.app.tool;

import com.nick.app.domain.model.DtoModel;
import com.nick.app.domain.model.EntityModel;

public class ConverterFactory {
    private Converter converter;

    public ConverterFactory(Converter converter) {
        this.converter = converter;
    }

    public DtoModel toDto(EntityModel entity) {
        return converter.toDto(entity);
    }

    public EntityModel toEntity(DtoModel dto) {
        return converter.toEntity(dto);
    }
}
