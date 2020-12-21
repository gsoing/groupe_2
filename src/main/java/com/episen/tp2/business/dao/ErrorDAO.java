package com.episen.tp2.business.dao;

import com.episen.tp2.business.model.Error;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorDAO extends JpaRepository<Error, Long> {
}
