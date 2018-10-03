package com.nick.app.tool.converter;

import com.nick.app.domain.dto.PersonDto;
import com.nick.app.domain.entity.PersonEntity;
import com.nick.app.tool.Converter;

public class PersonConverter implements Converter<PersonDto, PersonEntity> {

    @Override
    public PersonDto toDto(PersonEntity entity) {
        return PersonDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .gender(entity.getGender())
                .birthDate(entity.getBirthDate())
                .build();
    }

    @Override
    public PersonEntity toEntity(PersonDto dto) {
        return PersonEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .surname(dto.getSurname())
                .gender(dto.getGender())
                .birthDate(dto.getBirthDate())
                .build();
    }
}
