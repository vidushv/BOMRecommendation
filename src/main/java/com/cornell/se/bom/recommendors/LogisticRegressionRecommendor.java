package com.cornell.se.bom.recommendors;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cornell.se.bom.model.MAST;
import com.cornell.se.bom.model.MISCELLANEOUS;
import com.cornell.se.bom.model.MiscIdentity;
import com.cornell.se.bom.model.STPO;
import com.cornell.se.bom.model.StpoIdentity;
import com.cornell.se.bom.service.CDPOSService;

public class LogisticRegressionRecommendor implements Recommendor{
	
	Logger logger = LoggerFactory.getLogger(LogisticRegressionRecommendor.class);
	
	@Override
	public List<STPO> getRecommendations(STPO stpoFromUI, CDPOSService cdposService) throws IOException, InterruptedException {
		
		StpoIdentity stpoToRemove  = stpoFromUI.getStpoIdentity();
		logger.info(cdposService.toString());
		logger.info(stpoFromUI.getMATKL());
		
		List<STPO> otherSTPOS = cdposService.getSTPOByMATKLK(stpoFromUI.getMATKL(),stpoToRemove);
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("/home/mukul/git/BOMRecommendation/machine_learning/csvfile.csv"));

		writer.write("ID,Plant,Storage Location,Material Group,Volume,Purchasing Group,MRP Group,Lot Size,Stock,Availbility");
		writer.newLine();
		
		otherSTPOS.add(0,cdposService.getSTPOById(stpoToRemove));
		
		for (STPO stpo :otherSTPOS) {
			
			List<MAST> masts = cdposService.getMASTfromSTLNR(stpo.getStpoIdentity().getSTLNR());
			MiscIdentity identity = new MiscIdentity();
			identity.setSTLKN(stpo.getStpoIdentity().getSTLKN());
			identity.setSTLNR(stpo.getStpoIdentity().getSTLNR());
			identity.setSTPOZ(stpo.getStpoIdentity().getSTPOZ());
			List<MISCELLANEOUS> miscs = cdposService.getMiscsWithId(identity);
			
			for (MAST mast : masts) {

				for (MISCELLANEOUS misc : miscs) {

					String pGroup = misc.PURCHASING_GROUP.equals("")?"0":misc.PURCHASING_GROUP;
					String mGroup = misc.MRP_GROUP.equals("")?"0":misc.MRP_GROUP;
					
					writer.write(stpo.IDNRK + ","
							+ misc.WERKS + "," + misc.STORAGE_LOCATION + "," + misc.MATERIAL_GROUP + "," + misc.VOLUME
							+ "," + pGroup + "," + mGroup + "," + mast.LOSBS + "," + misc.STOCK + ","
							+ misc.AVAILABILITY);
					writer.newLine();
				}
			}
			
		}
 
		writer.close();

		ProcessBuilder builder = new ProcessBuilder("sh",
				"/home/mukul/git/BOMRecommendation/machine_learning/execute.sh", "/home/mukul/git/BOMRecommendation/machine_learning/csvfile.csv", RecommendationFactory.LOGISTIC_REGRESSION);
		Process p = builder.start();
		p.waitFor();

		BufferedReader brError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		BufferedReader brInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		
		String lineError ;
		
		while ((lineError = brError.readLine()) != null) {
			logger.info("DEBUG Error :: "+lineError);
		}
		
		List<STPO> recommendations = new ArrayList<STPO>();
	
		String lineSuc;

		while ((lineSuc = brInput.readLine()) != null) {
			
			logger.info("DEBUG Sh Success :: "+lineSuc);
			String idnrk = lineSuc.split(",")[0];
			String score = lineSuc.split(",")[1];
			STPO stpo = new STPO();
			stpo.setIDNRK(idnrk);
			stpo.setMEINS(score);
			recommendations.add(stpo);
			if (recommendations.size() == 9)
				break;
		}
		
		return recommendations;
	}

}
