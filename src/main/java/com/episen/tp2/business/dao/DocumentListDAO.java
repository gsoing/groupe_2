package com.episen.tp2.business.dao;

import com.episen.tp2.business.model.DocumentList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentListDAO extends JpaRepository<DocumentList, Long> {
}
