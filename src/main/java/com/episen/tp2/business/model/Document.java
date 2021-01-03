package com.episen.tp2.business.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.security.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name="document")
public class Document implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long documentid;

    @Column (name="created")
    private Timestamp created;

    @Column(name="updated")
    private java.security.Timestamp updated;

    @Column(name="title")
    private String title;

    @Column(name="creator")
    private String creator;

    @Column(name="editor")
    private String editor;

    @Column(name="body")
    private String body;

    @Enumerated(EnumType.STRING)
    private DocumentStatusEnum statusdocument;

    @ManyToOne(cascade = CascadeType.ALL)
    // heu c'est quoi ca ?
    private DocumentList documentlist;


    public DocumentSummary  toDocumentSummary() {
        return DocumentSummary.builder()
                .created(created)
                .updated(updated)
                .title(title)
                .build();

    }
}
