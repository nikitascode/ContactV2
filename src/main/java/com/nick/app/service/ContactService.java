package com.nick.app.service;

import com.nick.app.domain.dto.ContactDto;
import com.nick.app.domain.entity.ContactEntity;
import com.nick.app.repository.ContactRepository;
import com.nick.app.tool.ConverterFactory;
import com.nick.app.tool.converter.ContactConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService implements CrudService<ContactDto> {

    private ContactRepository contactRepository;

    private ConverterFactory converterFactory = new ConverterFactory(new ContactConverter());

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<ContactDto> findAll() {
        return contactRepository.findAll()
                .stream()
                .map(contactEntity -> (ContactDto) converterFactory.toDto(contactEntity))
                .collect(Collectors.toList());
    }

    @Override
    public ContactDto findById(Long id) {
        return (ContactDto) converterFactory.toDto(contactRepository.findById(id).get());
    }

    @Override
    public ContactDto save(ContactDto contactDto) {
        contactRepository.save((ContactEntity) converterFactory.toEntity(contactDto));
        return contactDto;
    }

    @Override
    public List<ContactDto> saveAll(List<ContactDto> contactsDto) {
        contactRepository.saveAll(contactsDto
                .stream()
                .map(contactDto -> (ContactEntity) converterFactory.toEntity(contactDto))
                .collect(Collectors.toList()));
        return contactsDto;
    }

    @Override
    public void delete(ContactDto contactDto) {
        contactRepository.delete((ContactEntity) converterFactory.toEntity(contactDto));
    }

    @Override
    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }
}
