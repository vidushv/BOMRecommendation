package com.cornell.se.bom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cornell.se.bom.model.MISCELLANEOUS;

@Repository
public interface MiscRepository extends JpaRepository<MISCELLANEOUS, Long> {

}
