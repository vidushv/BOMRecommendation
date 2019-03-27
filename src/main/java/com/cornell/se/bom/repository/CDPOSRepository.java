package com.cornell.se.bom.repository;

import com.cornell.se.bom.model.CDPOS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CDPOSRepository extends JpaRepository<CDPOS, Long> {

}
