package com.nick.app.service;

import com.nick.app.domain.dto.PersonDto;
import com.nick.app.domain.entity.PersonEntity;
import com.nick.app.repository.ContactRepository;
import com.nick.app.repository.PersonRepository;
import com.nick.app.tool.ConverterFactory;
import com.nick.app.tool.converter.PersonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService implements CrudService<PersonDto> {

    private PersonRepository personRepository;

    @Autowired
    private ContactRepository contactRepository;

    private ConverterFactory converterFactory = new ConverterFactory(new PersonConverter());

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<PersonDto> findAll() {
        return personRepository.findAll()
                .stream()
                .map(personEntity -> (PersonDto) converterFactory.toDto(personEntity))
                .collect(Collectors.toList());
    }

    @Override
    public PersonDto findById(Long id) {
        return (PersonDto) converterFactory.toDto(personRepository.findById(id).get());
    }

    @Override
    public PersonDto save(PersonDto personDto) {
        personRepository.save((PersonEntity) converterFactory.toEntity(personDto));
        return personDto;
    }

    @Override
    public List<PersonDto> saveAll(List<PersonDto> personsDto) {
        personRepository.saveAll(personsDto
                .stream()
                .map(personDto -> (PersonEntity) converterFactory.toEntity(personDto))
                .collect(Collectors.toList()));
        return personsDto;
    }

    public List<PersonDto> findPeopleByBirthDateBetween(Date firstDate, Date secondDate) {
        return personRepository.findByBirthDateAfterAndBirthDateBefore(firstDate, secondDate)
                .stream()
                .map(personEntity -> (PersonDto) converterFactory.toDto(personEntity))
                .collect(Collectors.toList());
    }

    public List<PersonDto> findPeopleByEmail(String emailPattern) {
        return contactRepository.findByEmailLike(emailPattern)
                .stream()
                .map(contactEntity -> (PersonDto) contactEntity.getOwner())
                .collect(Collectors.toList());
    }

    @Override
    public void delete(PersonDto personDto) {
        personRepository.delete((PersonEntity) converterFactory.toEntity(personDto));
    }

    @Override
    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }
}
