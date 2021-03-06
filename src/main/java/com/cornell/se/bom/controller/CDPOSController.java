package com.cornell.se.bom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cornell.se.bom.model.CDPOS;
import com.cornell.se.bom.model.MAST;
import com.cornell.se.bom.model.MISCELLANEOUS;
import com.cornell.se.bom.model.STPO;
import com.cornell.se.bom.service.impl.CDPOSServiceImpl;


@RestController
@RequestMapping("/api")
public class CDPOSController {

    @Autowired
    CDPOSServiceImpl CDPOSservice;

    /**
     * @return
     */
    @Transactional
    @GetMapping("/CDPOS")
    public List<CDPOS> getAllCDPOS() {
    	List<CDPOS> cdposs =  CDPOSservice.getAllCDPOS();
    	System.out.println("CDPOS"+cdposs);
    	return cdposs;
    }
    
    /**
     * 
     */
    @Transactional
    @PostMapping("/CDPOSInsert")
    public void insertCDPOS() {
    	CDPOSservice.insertCDPOS();
    }

    /**
     * @return
     */
    @Transactional
    @GetMapping("/misc")
    public List<MISCELLANEOUS> getAllMisc() {
    	List<MISCELLANEOUS> miscs =  CDPOSservice.getAllMiscellaneous();
    	System.out.println("MISCS"+miscs);
    	return miscs;
    }
    
    /**
     * @param startsWith
     * @return
     */
    @Transactional
    @GetMapping("/stpo")
    public List<STPO> getAllStpo(@RequestParam("name_startsWith") String startsWith) {
    	List<STPO> stpos =  CDPOSservice.getAllSTPOStartingWith(startsWith);
    	System.out.println("STPOS"+stpos);
    	return stpos;
    }

    /**
     * @return
     */
    @Transactional
    @GetMapping("/mast")
    public List<MAST> getAllMast() {
    	List<MAST> masts =  CDPOSservice.getAllMast();
    	System.out.println("MAST"+masts);
    	return masts;
    }
    
    
}

