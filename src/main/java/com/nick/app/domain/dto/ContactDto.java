package com.nick.app.domain.dto;

import com.nick.app.domain.model.DtoModel;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class ContactDto implements DtoModel {
    protected Long id;
    protected String email;
    protected String phonenumber;
}
