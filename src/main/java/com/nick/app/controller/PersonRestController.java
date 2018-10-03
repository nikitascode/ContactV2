package com.nick.app.controller;

import com.nick.app.domain.dto.PersonDto;
import com.nick.app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonRestController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<PersonDto> findAllPersons() {
        return personService.findAll();
    }

    @GetMapping("/{personId}")
    public PersonDto findPersonById(@PathVariable Long personId) {
        return personService.findById(personId);
    }

    @PostMapping
    public Long savePerson(@RequestBody PersonDto personDto) {
        personDto.setId(null);
        return personService.save(personDto).getId();
    }

    @PutMapping
    public PersonDto updatePerson(@RequestBody PersonDto personDto) {
        return personService.save(personDto);
    }

    @DeleteMapping("/{personId}")
    public void deletePersonById(@PathVariable Long personId) {
        personService.deleteById(personId);
    }

    @DeleteMapping
    public void deletePerson(@RequestBody PersonDto personDto) {
        personService.delete(personDto);
    }

    @GetMapping("/findInDate")
    public List<PersonDto> findInDate(@RequestParam("firstDate") Date firstDate, @RequestParam("secondDate") Date secondDate) {
        return personService.findPeopleByBirthDateBetween(firstDate, secondDate);
    }

    @GetMapping("/findByEmail")
    public List<PersonDto> findByEmail(@RequestParam("emailPattern") String emailPattern) {
        return personService.findPeopleByEmail(emailPattern);
    }
}
