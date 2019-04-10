package com.cornell.se.bom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cornell.se.bom.forms.BOMSearchForm;
import com.cornell.se.bom.model.CDPOS;
import com.cornell.se.bom.model.MISCELLANEOUS;
import com.cornell.se.bom.model.STPO;
import com.cornell.se.bom.service.impl.CDPOSServiceImpl;


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

    @Transactional
    @GetMapping("/misc")
    public List<MISCELLANEOUS> getAllMisc() {
    	List<MISCELLANEOUS> miscs =  CDPOSservice.getAllMiscellaneous();
    	System.out.println("MISCS"+miscs);
    	return miscs;
    }
    
    @Transactional
    @GetMapping("/stpo")
    public List<STPO> getAllStpo() {
    	List<STPO> stpos =  CDPOSservice.getAllSTPO();
    	System.out.println("MISCS"+stpos);
    	return stpos;
    }
    
}

