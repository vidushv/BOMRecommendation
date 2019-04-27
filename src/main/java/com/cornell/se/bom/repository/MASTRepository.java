package com.cornell.se.bom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cornell.se.bom.model.MAST;

@Repository
public interface MASTRepository extends JpaRepository<MAST, Long> {

}
