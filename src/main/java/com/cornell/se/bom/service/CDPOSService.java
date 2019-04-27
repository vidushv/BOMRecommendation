package com.cornell.se.bom.service;

import java.util.List;

import com.cornell.se.bom.model.CDPOS;
import com.cornell.se.bom.model.MAST;
import com.cornell.se.bom.model.MISCELLANEOUS;
import com.cornell.se.bom.model.MiscIdentity;
import com.cornell.se.bom.model.STPO;
import com.cornell.se.bom.model.StpoIdentity;

public interface CDPOSService {
	
	public List<CDPOS> getAllCDPOS();
	public void insertCDPOS();
	
	public List<MISCELLANEOUS> getAllMiscellaneous();
	
	public List<STPO> getAllSTPO();
	public List<MAST> getAllMast();
	List<STPO> getAllSTPOStartingWith(String startsWith);
	List<MAST> getMASTfromSTLNR(String STLNR);
	public List<MISCELLANEOUS> getMiscsWithId(MiscIdentity identity);
	public STPO getSTPOById(StpoIdentity id);
}
