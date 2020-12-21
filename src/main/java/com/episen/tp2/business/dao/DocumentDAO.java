package com.episen.tp2.business.dao;

import com.episen.tp2.business.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentDAO extends JpaRepository<Document, Long> {
}
