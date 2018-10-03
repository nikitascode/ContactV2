package com.nick.app.domain.dto;

import com.nick.app.domain.model.DtoModel;
import com.nick.app.domain.model.Person;
import lombok.*;

import java.util.Date;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor @Builder
public class PersonDto implements Person, DtoModel {
    private Long id;
    private String name;
    private String surname;
    private String gender;
    private Date birthDate;
}
