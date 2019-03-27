package com.cornell.se.bom.controller;

import com.cornell.se.bom.model.CDPOS;
import com.cornell.se.bom.repository.CDPOSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CDPOSController {

    @Autowired
    CDPOSRepository cdposRepository;

    @Transactional
    @GetMapping("/CDPOS")
    public List<CDPOS> getAllCDPOS() {
    	List<CDPOS> cdposs =  cdposRepository.findAll();
    	System.out.println("CDPOS"+cdposs);
    	return cdposs;
    }
    
    @Transactional
    @PostMapping("/CDPOSInsert")
    public void insertCDPOS() {
    	CDPOS toinsert = new CDPOS();
    	toinsert.setAENNR("aennr");
    	cdposRepository.save(toinsert);
    	cdposRepository.flush();
    }

}

