package com.cornell.se.bom.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cornell.se.bom.model.CDPOS;
import com.cornell.se.bom.model.MAST;
import com.cornell.se.bom.model.MISCELLANEOUS;
import com.cornell.se.bom.model.MiscIdentity;
import com.cornell.se.bom.model.STPO;
import com.cornell.se.bom.model.StpoIdentity;
import com.cornell.se.bom.repository.CDPOSRepository;
import com.cornell.se.bom.repository.MASTRepository;
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
    
    @Autowired
    MASTRepository mastRepository;
    

	/* (non-Javadoc)
	 * @see com.cornell.se.bom.service.CDPOSService#getAllCDPOS()
	 */
	@Override
	public List<CDPOS> getAllCDPOS() {
	
		return cdposRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.cornell.se.bom.service.CDPOSService#insertCDPOS()
	 */
	@Override
	public void insertCDPOS() {
	   	CDPOS toinsert = new CDPOS();
    	toinsert.setAENNR("aennr2");
    	toinsert.setOLD_MATNR("old2");
    	cdposRepository.save(toinsert);
    	cdposRepository.flush();
		
	}

	/* (non-Javadoc)
	 * @see com.cornell.se.bom.service.CDPOSService#getAllMiscellaneous()
	 */
	@Override
	public List<MISCELLANEOUS> getAllMiscellaneous() {
		// TODO Auto-generated method stub
		return miscRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.cornell.se.bom.service.CDPOSService#getAllSTPO()
	 */
	@Override
	public List<STPO> getAllSTPO() {
		return stpoRepository.findAll();
	}
	
	/* (non-Javadoc)
	 * @see com.cornell.se.bom.service.CDPOSService#getAllSTPOStartingWith(java.lang.String)
	 */
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

	/* (non-Javadoc)
	 * @see com.cornell.se.bom.service.CDPOSService#getAllMast()
	 */
	public List<MAST> getAllMast() {
		return mastRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.cornell.se.bom.service.CDPOSService#getMASTfromSTLNR(java.lang.String)
	 */
	@Override
	public List<MAST> getMASTfromSTLNR(String STLNR) {
		
		List<MAST> masts = mastRepository.findAll();
		
		List<MAST> res = new ArrayList<MAST>();
		for (MAST mast : masts) {
			if (mast.getIdentity().STLNR.equals(STLNR)) {
				res.add(mast);
			}
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see com.cornell.se.bom.service.CDPOSService#getMiscsWithId(com.cornell.se.bom.model.MiscIdentity)
	 */
	@Override
	public List<MISCELLANEOUS> getMiscsWithId(MiscIdentity identity) {
		
		List<MISCELLANEOUS> miscs = getAllMiscellaneous();
		
		List<MISCELLANEOUS> res = new ArrayList<MISCELLANEOUS>();
		
		for(MISCELLANEOUS mis : miscs) {
			
			if (mis.getIdentity().equals(identity))
				res.add(mis);
			
		}
		
		return res;
	}

	/* (non-Javadoc)
	 * @see com.cornell.se.bom.service.CDPOSService#getSTPOById(com.cornell.se.bom.model.StpoIdentity)
	 */
	@Override
	public STPO getSTPOById(StpoIdentity id) {
		List<STPO> stpos = getAllSTPO();
		
		STPO res = new STPO();
		for(STPO stpo : stpos) {
			
			if (stpo.getStpoIdentity().equals(id))
				res = stpo;
			
		}
		
		return res;
	}

	/* (non-Javadoc)
	 * @see com.cornell.se.bom.service.CDPOSService#getSTPOByMATKLK(java.lang.String, com.cornell.se.bom.model.StpoIdentity)
	 */
	@Override
	public List<STPO> getSTPOByMATKLK(String mATKL, StpoIdentity toRemove) {
		List<STPO> stpos = getAllSTPO();
		
		List<STPO> res = new ArrayList<STPO>();
		for(STPO stpo : stpos) {
			
			if (stpo.getMATKL().equals(mATKL) && !stpo.getStpoIdentity().equals(toRemove))
				res.add(stpo);
			
		}
		
		return res;
	}

}
