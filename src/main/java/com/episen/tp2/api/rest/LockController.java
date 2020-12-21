package com.episen.tp2.api.rest;

import com.episen.tp2.application.manager.DocumentManagerImpl;
import com.episen.tp2.business.dto.Lock;
import com.episen.tp2.business.exception.ConflictException;
import com.episen.tp2.business.exception.ForbiddenException;
import com.episen.tp2.business.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LockController {

    public DocumentManagerImpl documentManager;

    // Get lock
    @RequestMapping(value = "/documents/{documentId}/lock", produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<Lock> documentsDocumentIdLockGet(@PathVariable("documentId") Long documentId) {

        if(documentManager.getDocumentById(documentId).equals(null)){
            throw new NotFoundException("Document not found");
        }
        Optional<com.episen.tp2.business.model.Lock> lock = documentManager.getLock(documentId);

        try {
            lock.equals(null);
        } catch (NullPointerException e) {
            throw new NotFoundException("Document not locked");
        }

        Lock lockDto = lock.get().toDto();
        return new ResponseEntity<Lock>(lockDto, HttpStatus.FOUND);
    }

    // Delete Lock
    @RequestMapping(value = "/documents/{documentId}/lock", produces = {"application/json"}, method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> documentsDocumentIdLockDelete(@PathVariable("documentId") Long documentId) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        if(documentManager.getDocumentById(documentId).equals(null)){
            throw new NotFoundException("Document not found");
        }

        Boolean deleted;
        try {
            deleted = documentManager.deleteLock(documentId, user);
        } catch (NullPointerException e) {
            throw new NotFoundException("Document not locked");
        }

        if (deleted) {
            return new ResponseEntity<Boolean>(HttpStatus.OK);
        } else {
            throw new ForbiddenException("You can't unlock this document");
        }
    }

    // Put lock

    @RequestMapping(value = "/documents/{documentId}/lock", produces = {"application/json"}, method = RequestMethod.PUT)
    public ResponseEntity<Lock> documentsDocumentIdLockPut(@PathVariable("documentId") Long documentId) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        if(documentManager.getDocumentById(documentId).equals(null)){
            throw new NotFoundException("Document not found");
        }

        com.episen.tp2.business.model.Lock addedLock = documentManager.addLock(documentId, user);
        try {
            addedLock.equals(null);
        } catch (NullPointerException e) {
            throw new ConflictException("Document already locked");
        }
        Lock lockDto = addedLock.toDto();

        return new ResponseEntity<Lock>(lockDto, HttpStatus.CREATED);
    }


}
