package com.cornell.se.bom.recommendors;

import java.io.IOException;
import java.util.List;

import com.cornell.se.bom.model.STPO;
import com.cornell.se.bom.service.CDPOSService;

public interface Recommendor {
	
	public List<STPO> getRecommendations(STPO stpo,CDPOSService cdposService) throws IOException, InterruptedException;

}
