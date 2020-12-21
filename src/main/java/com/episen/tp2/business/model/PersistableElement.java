package com.episen.tp2.business.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class PersistableElement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    public PersistableElement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
