package com.cornell.se.bom.controller;

import com.cornell.se.bom.model.CDPOS;
import com.cornell.se.bom.repository.CDPOSRepository;
import com.cornell.se.bom.service.CDPOSService;
import com.cornell.se.bom.service.impl.CDPOSServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CDPOSController {

    @Autowired
    CDPOSServiceImpl CDPOSservice;

    @Transactional
    @GetMapping("/CDPOS")
    public List<CDPOS> getAllCDPOS() {
    	List<CDPOS> cdposs =  CDPOSservice.getAllCDPOS();
    	System.out.println("CDPOS"+cdposs);
    	return cdposs;
    }
    
    @Transactional
    @PostMapping("/CDPOSInsert")
    public void insertCDPOS() {
    	CDPOSservice.insertCDPOS();
    }

}

