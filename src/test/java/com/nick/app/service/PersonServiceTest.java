package com.nick.app.service;

import com.nick.app.domain.dto.PersonDto;
import com.nick.app.domain.entity.PersonEntity;
import com.nick.app.repository.PersonRepository;
import com.nick.app.tool.ConverterFactory;
import com.nick.app.tool.converter.PersonConverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PersonServiceTest {
    static final String DATE_FORMAT = "dd/MM/yyyy";
    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    @InjectMocks
    PersonService personService;

    @Mock
    PersonRepository personRepository;

    List<PersonEntity> personEntities;
    ConverterFactory converterFactory;

    @Before
    public void init() throws ParseException {
        MockitoAnnotations.initMocks(this);
        converterFactory = new ConverterFactory(new PersonConverter());

        Instant nick = LocalDate.of(1990, Month.FEBRUARY, 11).atStartOfDay(ZoneId.of("Europe/Warsaw")).toInstant();
        Instant nina = LocalDate.of(2001, Month.JULY, 25).atStartOfDay(ZoneId.of("Europe/Warsaw")).toInstant();
        Instant anna = LocalDate.of(1997, Month.SEPTEMBER, 4).atStartOfDay(ZoneId.of("Europe/Warsaw")).toInstant();

        personEntities = new ArrayList<>();
        personEntities.add(PersonEntity.builder().id(1L).name("Nick").surname("Lol").gender("male").birthDate(Date.from(nick)).build());
        personEntities.add(PersonEntity.builder().id(2L).name("Nina").surname("Back").gender("female").birthDate(Date.from(nina)).build());
        personEntities.add(PersonEntity.builder().id(3L).name("Anna").surname("Long").gender("female").birthDate(Date.from(anna)).build());
    }

    @Test
    public void findAll() {

        Mockito.when(personRepository.findAll()).thenReturn(personEntities);

        List<PersonDto> personsDto = personService.findAll();
        Assert.assertEquals(personsDto.size(), personEntities.size());
        Assert.assertEquals(personsDto.get(0).getId(), personEntities.get(0).getId());
    }

    @Test
    public void findById() {
        Mockito.when(personRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(personEntities.get(0)));

        PersonDto personDto = personService.findById(1L);
        Assert.assertEquals(personDto.getName(), personEntities.get(0).getName());
        Assert.assertEquals(personDto.getSurname(), personEntities.get(0).getSurname());
    }

    @Test
    public void save() throws ParseException {
        PersonEntity newPerson = PersonEntity.builder().id(10L).name("Sasha").surname("Ibica").gender("male")
                .birthDate(dateFormat.parse("04/08/2000")).build();
        Mockito.when(personRepository.save(newPerson)).thenReturn(newPerson);

        PersonDto personDto = personService.save((PersonDto) converterFactory.toDto(newPerson));
        Assert.assertEquals(newPerson.getId(), personDto.getId());
        Assert.assertEquals(newPerson.getBirthDate(), personDto.getBirthDate());
    }

    @Test
    public void saveAll() throws ParseException {
        List<PersonEntity> personEntitiesTest = new ArrayList<>();
        personEntitiesTest.add(PersonEntity.builder().id(11L).name("Lola").surname("Bark").gender("female")
                .birthDate(dateFormat.parse("15/12/1989")).build());

        Mockito.when(personRepository.saveAll(personEntitiesTest)).thenReturn(personEntitiesTest);
        Mockito.when(personRepository.findById(11L)).thenReturn(java.util.Optional.ofNullable(personEntitiesTest.get(0)));

        List<PersonDto> personDtosTest = personService.saveAll(personEntitiesTest.stream()
                .map(personEntity -> (PersonDto) converterFactory.toDto(personEntity)).collect(Collectors.toList()));
        PersonDto personDtoTest = personService.findById(11L);

        Assert.assertEquals(personDtosTest.size(), personEntitiesTest.size());
        Assert.assertEquals(personDtoTest.getId(), personEntitiesTest.get(0).getId());
    }

    @Test
    public void findPeopleByBirthDateBetween() throws ParseException {
        Instant firstDate = LocalDate.of(1998, Month.DECEMBER, 1).atStartOfDay(ZoneId.of("Europe/Warsaw")).toInstant();
        Instant secondDate = LocalDate.of(2001, Month.DECEMBER, 1).atStartOfDay(ZoneId.of("Europe/Warsaw")).toInstant();
        Mockito.when(personRepository.findByBirthDateAfterAndBirthDateBefore(Date.from(firstDate), Date.from(secondDate)))
                .thenReturn(personEntities);

        List<PersonDto> personDtos = personService.findPeopleByBirthDateBetween(Date.from(firstDate), Date.from(secondDate));
        personDtos.stream().forEach(personDto -> System.out.println(personDto.getName()));
        Assert.assertEquals(personDtos.size(), personEntities.size());
        Assert.assertEquals(personDtos.get(0).getId(), personEntities.get(0).getId());
    }

    @Test
    public void findPeopleByEmail() {
        // TODO: 10/3/18
    }

    @Test
    public void delete() {
        PersonEntity nina = personEntities.get(1);
        personRepository.delete(nina);

        Mockito.when(personRepository.findAll()).thenReturn(personEntities);

        personService.delete((PersonDto) converterFactory.toDto(nina));
        List<PersonDto> personDtos = personService.findAll();
        Assert.assertEquals(personDtos.size(), personEntities.size());
        Assert.assertEquals(personDtos.get(1).getId(), personEntities.get(1).getId());
    }

    @Test
    public void deleteById() {
        PersonEntity nina = personEntities.get(1);
        personService.deleteById(nina.getId());

        Mockito.when(personRepository.findAll()).thenReturn(personEntities);

        personService.deleteById(nina.getId());
        List<PersonDto> personDtos = personService.findAll();
        Assert.assertEquals(personDtos.size(), personEntities.size());
        Assert.assertEquals(personDtos.get(1).getId(), personEntities.get(1).getId());
    }
}