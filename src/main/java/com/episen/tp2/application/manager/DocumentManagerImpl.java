package com.episen.tp2.application.manager;

import com.episen.tp2.business.OrikaBeanMapper;
import com.episen.tp2.business.dao.DocumentDAO;
import com.episen.tp2.business.dao.LockDAO;
import com.episen.tp2.business.dto.DocumentDTO;
import com.episen.tp2.business.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentManagerImpl implements DocumentManager{

    private final OrikaBeanMapper orikaBeanMapper;

    private final DocumentDAO documentDAO;

    private final LockDAO lockDAO;


    public DocumentManagerImpl(OrikaBeanMapper orikaBeanMapper, DocumentDAO documentDAO, LockDAO lockDAO) {
        this.orikaBeanMapper = orikaBeanMapper;
        this.documentDAO = documentDAO;
        this.lockDAO = lockDAO;
    }


    @Override
    public DocumentDTO getDocumentById(Long documentId) {
        Document document = documentDAO.findById(documentId).orElse(null);
        return orikaBeanMapper.map(document, DocumentDTO.class);
    }

    @Override
    public DocumentDTO putDocumentById(Long documentId, DocumentDTO documentDTO, String user) {
        Document documentToFind = documentDAO.findById(documentId).orElse(null);
        Document documentPut = orikaBeanMapper.map(documentDTO, Document.class);

        documentToFind.setBody(documentPut.getBody());
        documentToFind.setCreated(documentPut.getCreated());
        documentToFind.setCreator(documentPut.getCreator());
        documentToFind.setEditor(documentPut.getEditor());
        documentToFind.setDocumentlist(documentPut.getDocumentlist());
        documentToFind.setTitle(documentPut.getTitle());
        documentToFind.setUpdated(documentPut.getUpdated());
        documentToFind.setDocumentlist(documentPut.getDocumentlist());

        documentDAO.save(documentToFind);
        return orikaBeanMapper.map(documentToFind, DocumentDTO.class);
    }

    @Override
    public void putDocumentStatusById(Long documentId, DocumentStatusEnum documentStatusEnum) {
        Document documentToFind = documentDAO.findById(documentId).orElse(null);
        documentToFind.setStatusdocument(documentStatusEnum);
        documentDAO.save(documentToFind);
    }

    @Override
    public DocumentList getAll(Pageable pageable) {
        Page<Document> documentPage = documentDAO.findAll(pageable);
        List<DocumentSummary> documentSummaryList = new ArrayList<DocumentSummary>();
        com.episen.tp2.business.model.DocumentList documentList = new com.episen.tp2.business.model.DocumentList();
        Iterator<Document> documentIterator = documentPage.iterator();
        while (documentIterator.hasNext()){
            Document document = documentIterator.next();
            documentSummaryList.add(document.toDocumentSummary());
        }
        documentList.setData(documentSummaryList);
        documentList.setNbElements(pageable.getPageSize());
        documentList.setPage(pageable.getPageNumber());
        return documentList;
    }

    @Override
    public DocumentSummary postDocumentById(Document document, String creator) {
        document.setStatusdocument(DocumentStatusEnum.CREATED);
        document.setCreator(creator);
        document.setEditor(creator);
        documentDAO.save(document);
        DocumentSummary documentSummary = document.toDocumentSummary();
        return documentSummary;
    }

    @Override
    public Lock addLock(Long documentId, String user) {
        if(lockDAO.findById(documentId).equals(null)){
            Lock lock = new Lock();
            lock.setOwner(user);
            lock.setId(documentId);
            lock = lockDAO.save(lock);
            return lock;
        }
        return null;
    }

    @Override
    public Optional<Lock> getLock(Long documentId) {
        return lockDAO.findById(documentId);
    }

    @Override
    public Boolean deleteLock(Long documentId, String user) {
        Optional<Lock> existingLock = getLock(documentId);

        if(existingLock.get().getOwner().equals(user)){
            lockDAO.deleteById(documentId);
            return true;
        }
        else {
            return false;
        }

    }


}
