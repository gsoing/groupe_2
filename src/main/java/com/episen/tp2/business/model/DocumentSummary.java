package com.episen.tp2.business.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.security.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentSummary {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long documentId;

    @Column (name="description")
    private String description;

    @Column (name="created")
    private Timestamp created;

    @Column(name="updated")
    private Timestamp updated;

    @Column(name="title")
    private String title;
}
