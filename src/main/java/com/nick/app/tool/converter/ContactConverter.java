package com.nick.app.tool.converter;

import com.nick.app.domain.dto.ContactDto;
import com.nick.app.domain.entity.ContactEntity;
import com.nick.app.tool.Converter;

public class ContactConverter implements Converter<ContactDto, ContactEntity> {

    @Override
    public ContactDto toDto(ContactEntity entity) {
        return ContactDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .phonenumber(entity.getPhonenumber())
                .build();
    }

    @Override
    public ContactEntity toEntity(ContactDto dto) {
        return ContactEntity.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .phonenumber(dto.getPhonenumber())
                .build();
    }
}
