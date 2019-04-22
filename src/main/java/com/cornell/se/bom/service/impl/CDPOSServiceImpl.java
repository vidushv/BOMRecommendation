package com.cornell.se.bom.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cornell.se.bom.model.CDPOS;
import com.cornell.se.bom.model.MISCELLANEOUS;
import com.cornell.se.bom.model.STPO;
import com.cornell.se.bom.repository.CDPOSRepository;
import com.cornell.se.bom.repository.MiscRepository;
import com.cornell.se.bom.repository.StpoRepository;
import com.cornell.se.bom.service.CDPOSService;

public class CDPOSServiceImpl implements CDPOSService{
	
    @Autowired
    CDPOSRepository cdposRepository;
    
    @Autowired
    MiscRepository miscRepository;
    
    @Autowired
    StpoRepository stpoRepository;
    

	@Override
	public List<CDPOS> getAllCDPOS() {
	
		return cdposRepository.findAll();
	}

	@Override
	public void insertCDPOS() {
	   	CDPOS toinsert = new CDPOS();
    	toinsert.setAENNR("aennr2");
    	toinsert.setOLD_MATNR("old2");
    	cdposRepository.save(toinsert);
    	cdposRepository.flush();
		
	}

	@Override
	public List<MISCELLANEOUS> getAllMiscellaneous() {
		// TODO Auto-generated method stub
		return miscRepository.findAll();
	}

	@Override
	public List<STPO> getAllSTPO() {
		return stpoRepository.findAll();
	}
	
	@Override
	public List<STPO> getAllSTPOStartingWith(String startsWith) {
		List<STPO> allstpo = stpoRepository.findAll();
		List<STPO> result = new ArrayList<STPO>();
		
		for (STPO stpo: allstpo) {
			
			if (stpo.IDNRK.toLowerCase().contains(startsWith.toLowerCase())) {
				result.add(stpo);
			} 
				
		}
		
		return result;
	}

}
