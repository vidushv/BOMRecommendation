package com.cornell.se.bom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cornell.se.bom.model.STPO;

@Repository
public interface StpoRepository extends JpaRepository<STPO, Long> {
	

}
