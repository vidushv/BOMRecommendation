package com.cornell.se.bom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cornell.se.bom.model.MISCELLANEOUS;
import com.cornell.se.bom.model.STPO;

@Repository
public interface StpoRepository extends JpaRepository<STPO, Long> {

}
