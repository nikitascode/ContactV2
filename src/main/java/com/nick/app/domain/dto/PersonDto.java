package com.nick.app.domain.dto;

import com.nick.app.domain.model.DtoModel;
import lombok.*;

import java.util.Date;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor @Builder
public class PersonDto implements DtoModel {
    protected Long id;
    protected String name;
    protected String surname;
    protected String gender;
    protected Date birthDate;
}
