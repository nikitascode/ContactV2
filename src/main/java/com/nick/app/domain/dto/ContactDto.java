package com.nick.app.domain.dto;

import com.nick.app.domain.model.Contact;
import com.nick.app.domain.model.DtoModel;
import com.nick.app.domain.model.Person;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class ContactDto implements Contact, DtoModel {
    private Long id;
    private String email;
    private String phonenumber;
    private Person owner;
}
