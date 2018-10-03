package com.nick.app.domain.entity;

import com.nick.app.domain.model.EntityModel;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity @Table(name = "person")
@Setter @Getter @Builder
@AllArgsConstructor @NoArgsConstructor
public class PersonEntity implements EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String name;
    protected String surname;
    protected String gender;
    protected Date birthDate;
}
