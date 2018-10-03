package com.nick.app.tool;

import com.nick.app.domain.model.DtoModel;
import com.nick.app.domain.model.EntityModel;

public interface Converter<T extends DtoModel, I extends EntityModel> {
    T toDto(I entity);
    I toEntity(T dto);
}
