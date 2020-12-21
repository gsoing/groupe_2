package com.episen.tp2.api.rest;

import com.episen.tp2.application.manager.DocumentManager;
import com.episen.tp2.business.dto.DocumentDTO;
import com.episen.tp2.business.dto.DocumentList;
import com.episen.tp2.business.dto.DocumentSummary;
import com.episen.tp2.business.dto.Lock;
import com.episen.tp2.business.exception.BadRequestException;
import com.episen.tp2.business.exception.ConflictException;
import com.episen.tp2.business.exception.LockException;
import com.episen.tp2.business.exception.NotFoundException;
import com.episen.tp2.business.model.Document;
import com.episen.tp2.business.model.DocumentStatusEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DocumentController {

    private final DocumentManager documentManager;

    public DocumentController(DocumentManager documentManager) {
        this.documentManager = documentManager;
    }

    // Get all documents
    @RequestMapping(value = "/documents", produces = { "application/json" }, method = RequestMethod.GET)
    private ResponseEntity<DocumentList> getDocuments(@PageableDefault(page = 0, size = 20) Pageable pageable){
        com.episen.tp2.business.model.DocumentList documents = documentManager.getAll(pageable);
        return new ResponseEntity<DocumentList>((MultiValueMap<String, String>) documents, HttpStatus.FOUND);
    }


    // Post document
    @RequestMapping(value = "/documents", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
    private ResponseEntity<DocumentSummary> postDocument(@RequestBody DocumentDTO documentDTO){

        try {
            documentManager.getDocumentById(documentDTO.getDocumentid()).equals(null);
        } catch (NullPointerException e) {
            com.episen.tp2.business.model.DocumentSummary createdDocumentSummary = documentManager.postDocumentById(documentDTO.toEntity(), SecurityContextHolder.getContext().getAuthentication().getName());
            return new ResponseEntity<DocumentSummary>((MultiValueMap<String, String>) createdDocumentSummary, HttpStatus.CREATED);
        }
        throw new ConflictException("Already exist");
    }

    // Get document by Id
    @RequestMapping(value = "/documents/{documentId}", produces = { "application/json" }, method = RequestMethod.GET)
    private ResponseEntity<Document> getDocumentById(@PathVariable("documentId") Long documentId){
        Document document = documentManager.getDocumentById(documentId).toEntity();
        if(document.equals(null)){
            throw new NotFoundException("Any document with id " + documentId);
        }
        else return new ResponseEntity<Document>(document, HttpStatus.FOUND);
    }

    // Put document
    @RequestMapping(value = "/documents/{documentId}", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.PUT)
    private ResponseEntity<DocumentDTO> putDocumentById(@PathVariable("documentId") Long documentId, @RequestBody DocumentDTO documentDTO){
        DocumentDTO updatedDocument = null;
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<com.episen.tp2.business.model.Lock> lock = documentManager.getLock(documentId);
        boolean update = false;
        if(lock.equals(null)){
            update = true;
        }

        if(update || lock.get().getOwner().equals(user)){
            try {
                updatedDocument = documentManager.putDocumentById(documentId, documentDTO, user);
            } catch (NullPointerException nf){
                throw new NotFoundException("Document not found");
            }
            return new ResponseEntity<DocumentDTO>(updatedDocument, HttpStatus.OK);

        } else {
            throw new LockException("Document Locked");
        }

    }


    // Put document Status

    @RequestMapping(value = "/documents/{documentId}/status", produces = { "application/json" }, consumes = { "text/plain" }, method = RequestMethod.PUT)
    private ResponseEntity<Void> putDocumentStatusById(@PathVariable("documentId") Long documentId, @RequestBody DocumentStatusEnum documentStatusEnum){
        Document document = documentManager.getDocumentById(documentId).toEntity();

        if(document.getStatusdocument() != DocumentStatusEnum.VALIDATED){
            throw new BadRequestException("Document status must be VALIDATED");
        }
        try{
            documentManager.putDocumentStatusById(documentId,documentStatusEnum);
            } catch (NullPointerException np){
            throw new NotFoundException("Document not found");
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
