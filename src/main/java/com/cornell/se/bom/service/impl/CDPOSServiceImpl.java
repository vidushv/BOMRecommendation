package com.cornell.se.bom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cornell.se.bom.model.CDPOS;
import com.cornell.se.bom.repository.CDPOSRepository;
import com.cornell.se.bom.service.CDPOSService;

public class CDPOSServiceImpl implements CDPOSService{
	
    @Autowired
    CDPOSRepository cdposRepository;

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

}
