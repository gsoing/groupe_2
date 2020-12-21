package com.episen.tp2.business.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name="error")
public class Error extends PersistableElement{

    @Column(name="errorCode")
    private String errorCode;

    @Column(name="errorMessage")
    private String errorMessage;

//    @ManyToOne(cascade = CascadeType.ALL)
//    private ErrorDefinition errordefinition;
}
