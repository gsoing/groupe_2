package com.episen.tp2.business.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="documentlist")
public class DocumentList extends PersistableElement{

    @Column(name="page")
    private int page;

    @Column(name="nbElements")
    private int nbElements;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "documentlist", fetch = FetchType.EAGER)
    private List<DocumentSummary> data;



}
