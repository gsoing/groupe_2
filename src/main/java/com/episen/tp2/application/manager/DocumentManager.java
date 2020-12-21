package com.episen.tp2.application.manager;

import com.episen.tp2.business.dto.DocumentDTO;
import com.episen.tp2.business.model.*;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DocumentManager {
    DocumentDTO getDocumentById(Long documentId);

    DocumentDTO putDocumentById(Long documentId, DocumentDTO documentDTO, String user);

    void putDocumentStatusById(Long documentId, DocumentStatusEnum documentStatusEnum);

    DocumentList getAll(Pageable pageable);

    DocumentSummary postDocumentById(Document document, String creator);

    Lock addLock(Long documentId, String user);

    Optional<Lock> getLock(Long documentId);

    Boolean deleteLock(Long documentId, String user);

}
