package com.nick.app.domain.entity;

import com.nick.app.domain.model.Contact;
import com.nick.app.domain.model.EntityModel;
import com.nick.app.domain.model.Person;
import lombok.*;

import javax.persistence.*;

@Entity @Table(name = "contact")
@Setter @Getter @Builder
@AllArgsConstructor @NoArgsConstructor
public class ContactEntity implements Contact, EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String phonenumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person owner;
}
