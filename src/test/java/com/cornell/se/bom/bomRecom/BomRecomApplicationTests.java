package com.cornell.se.bom.bomRecom;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.cornell.se.bom.model.MISCELLANEOUS;
import com.cornell.se.bom.model.MiscIdentity;
import com.cornell.se.bom.model.STPO;
import com.cornell.se.bom.model.StpoIdentity;
import com.cornell.se.bom.service.CDPOSService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BomRecomApplicationTests {

	@Autowired
	CDPOSService cdposService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetSTPO() {

		StpoIdentity identity = new StpoIdentity();
		identity.setSTLKN("00000001");
		identity.setSTLNR("00000011");
		identity.setSTPOZ("00000004");

		List<STPO> stpos = cdposService.getSTPOByMATKLK("000000700", identity);
		STPO res = null;

		for (STPO stpo : stpos) {
			if (stpo.getStpoIdentity().equals(identity))
				res = stpo;
		}

		Assert.isNull(res);

	}
	
	@Test
	public void testgetSTPOById() {
		
		StpoIdentity identity = new StpoIdentity();
		identity.setSTLKN("00000001");
		identity.setSTLNR("00000011");
		identity.setSTPOZ("00000004");
		STPO stpo = cdposService.getSTPOById(identity);
		
		Assert.isTrue(stpo.getStpoIdentity().equals(identity));
		
	}
	
	@Test
	public void testgetMiscsWithId() {
		MiscIdentity identity = new MiscIdentity();
		identity.setSTLKN("00000001");
		identity.setSTLNR("00000011");
		identity.setSTPOZ("00000004");
		MISCELLANEOUS misc = cdposService.getMiscsWithId(identity).get(0);
		Assert.isTrue(misc.getIdentity().equals(identity));
	}
	
	@Test
	public void testgetAllSTPOStartingWith() {
		
		List<STPO> stpos = cdposService.getAllSTPOStartingWith("batt");
		
		for (STPO stpo : stpos) {
			Assert.isTrue(stpo.getIDNRK().toLowerCase().contains("batt"));
		}
		
	}

}
