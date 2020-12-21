package com.episen.tp2.business.dto;

import com.episen.tp2.business.model.Document;
import com.episen.tp2.business.model.DocumentStatusEnum;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DocumentDTO {

    private Long documentid;
    private String description;
    private java.security.Timestamp created;
    private java.security.Timestamp updated;
    private String title;
    private String creator;
    private String editor;
    private String body;
    private DocumentStatusEnum statusdocument;





    public Document toEntity() {
        return Document.builder()
                .documentid(documentid)
                .created(created)
                .updated(updated)
                .title(title)
                .creator(creator)
                .editor(editor)
                .body(body)
                .statusdocument(statusdocument)
                .build();
    }
}
