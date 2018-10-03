package com.nick.app.domain.entity;

import com.nick.app.domain.model.EntityModel;
import lombok.*;

import javax.persistence.*;

@Entity @Table(name = "contact")
@Setter @Getter @Builder
@AllArgsConstructor @NoArgsConstructor
public class ContactEntity implements EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String email;
    protected String phonenumber;
}
