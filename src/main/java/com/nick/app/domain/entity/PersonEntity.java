package com.nick.app.domain.entity;

import com.nick.app.domain.model.EntityModel;
import com.nick.app.domain.model.Person;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity @Table(name = "person")
@Setter @Getter @Builder
@AllArgsConstructor @NoArgsConstructor
public class PersonEntity implements Person, EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String surname;

    private String gender;

    @Temporal(TemporalType.DATE)
    private Date birthDate;
}
