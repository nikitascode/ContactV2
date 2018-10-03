package com.nick.app.controller;

import com.nick.app.domain.dto.ContactDto;
import com.nick.app.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/{personId}/contact")
public class ContactRestController {

    @Autowired
    ContactService contactService;

    @GetMapping
    public List<ContactDto> findAllContacts() {
        return contactService.findAll();
    }

    @GetMapping("/{contactId}")
    public ContactDto findContactById(@PathVariable Long contactId) {
        return contactService.findById(contactId);
    }

    @PostMapping
    public Long saveContact(@RequestBody ContactDto contactDto) {
        contactDto.setId(null);
        return contactService.save(contactDto).getId();
    }

    @PutMapping
    public ContactDto updateContact(@RequestBody ContactDto contactDto) {
        return contactService.save(contactDto);
    }

    @DeleteMapping("/{contactId}")
    public void deleteContactById(@PathVariable Long contactId) {
        contactService.deleteById(contactId);
    }

    @DeleteMapping
    public void deleteContacts(@RequestBody ContactDto contactDto) {
        contactService.delete(contactDto);
    }
}
