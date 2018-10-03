package com.nick.app.tool;

import com.nick.app.domain.model.DtoModel;
import com.nick.app.domain.model.EntityModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor @AllArgsConstructor
public class ConverterFactory {
    private Converter converter;

    public DtoModel toDto(EntityModel entity) {
        return converter.toDto(entity);
    }

    public EntityModel toEntity(DtoModel dto) {
        return converter.toEntity(dto);
    }
}
