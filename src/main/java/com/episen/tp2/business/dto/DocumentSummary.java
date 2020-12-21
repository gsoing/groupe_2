package com.episen.tp2.business.dto;

import lombok.*;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentSummary {
    private Long documentId;
    private String description;
    private Timestamp created;
    private Timestamp updated;
    private String title;

    }
