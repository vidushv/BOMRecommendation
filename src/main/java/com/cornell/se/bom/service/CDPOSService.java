package com.cornell.se.bom.service;

import java.util.List;

import com.cornell.se.bom.model.CDPOS;
import com.cornell.se.bom.model.MISCELLANEOUS;
import com.cornell.se.bom.model.STPO;

public interface CDPOSService {
	
	public List<CDPOS> getAllCDPOS();
	public void insertCDPOS();
	
	public List<MISCELLANEOUS> getAllMiscellaneous();
	
	public List<STPO> getAllSTPO();
	List<STPO> getAllSTPOStartingWith(String startsWith);
}
