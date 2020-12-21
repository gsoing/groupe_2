package com.episen.tp2.business.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name="errordefinition")
public class ErrorDefinition extends PersistableElement{

    @Enumerated(EnumType.STRING)
    private ErrorTypeEnum errorType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "errordefinition", fetch = FetchType.EAGER)
    private List<Error> errors;


}
