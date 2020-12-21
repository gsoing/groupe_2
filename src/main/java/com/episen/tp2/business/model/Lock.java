package com.episen.tp2.business.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name="lock")
public class Lock extends PersistableElement{

    @Column(name="owner")
    private String owner;

    @Column (name="created")
    private Timestamp created;


    public com.episen.tp2.business.dto.Lock toDto(){
        return com.episen.tp2.business.dto.Lock.builder()
                .created(created)
                .owner(owner)
                .build();
    }
}
