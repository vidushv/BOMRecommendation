package com.cornell.se.bom.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cornell.se.bom.forms.BOMSearchForm;
import com.cornell.se.bom.model.MAST;
import com.cornell.se.bom.model.MISCELLANEOUS;
import com.cornell.se.bom.model.MiscIdentity;
import com.cornell.se.bom.model.STPO;
import com.cornell.se.bom.model.StpoIdentity;
import com.cornell.se.bom.service.CDPOSService;

@Controller
public class WelcomeController {

	@Autowired
	CDPOSService cdposService;
	
	Logger logger = LoggerFactory.getLogger(WelcomeController.class);

	/**
	 * @return
	 */
	@GetMapping("/")
	public ModelAndView displayIndexPage() {
		BOMSearchForm bomSearchForm = new BOMSearchForm();
		ModelAndView mv = new ModelAndView("welcome", "bomSearchForm", bomSearchForm);
		return mv;
	}

	/**
	 * @param selectedIdentity
	 * @param selectedName
	 * @param MATKL
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@PostMapping("/welcome")
	public ModelAndView displaySearchPage(
			@RequestParam(value = "selectedIdentity", required = true) String selectedIdentity,
			@RequestParam(value = "selectedIDNRK", required = true) String selectedName,
			@RequestParam(value = "MATKL", required = true) String MATKL)
			throws IOException, InterruptedException {

		logger.info("Entering displaySearchPage");
		String SelectedStlnr = selectedIdentity.substring(selectedIdentity.indexOf("STLNR") + 8,selectedIdentity.indexOf("STLKN") - 3);
		String SelectedStlkn = selectedIdentity.substring(selectedIdentity.indexOf("STLKN") + 8,selectedIdentity.indexOf("STPOZ") - 3);
		String SelectedStpoz = selectedIdentity.substring(selectedIdentity.indexOf("STPOZ") + 8, selectedIdentity.length() - 2);
		StpoIdentity stpoToRemove  = new StpoIdentity();
		stpoToRemove.setSTLKN(SelectedStlkn);
		stpoToRemove.setSTLNR(SelectedStlnr);
		stpoToRemove.setSTPOZ(SelectedStpoz);
		
		List<STPO> otherSTPOS = cdposService.getSTPOByMATKLK(MATKL,stpoToRemove);
		
		BOMSearchForm bomSearchForm = new BOMSearchForm();
		bomSearchForm.setSelectedIDNRK(selectedName);
		bomSearchForm.setLoadedByPost(true);
		bomSearchForm.setSelectedIdentity(selectedIdentity);
		bomSearchForm.setMATKL(MATKL);
		
		BufferedWriter writer = new BufferedWriter(
				new FileWriter("/home/mukul/git/BOMRecommendation/machine_learning/csvfile.csv"));

		writer.write(
				"ID,Plant,Storage Location,Material Group,Volume,Purchasing Group,MRP Group,Lot Size,Stock,Availbility");
		writer.newLine();
		
		System.out.println(otherSTPOS);
		otherSTPOS.add(0,cdposService.getSTPOById(stpoToRemove));
		
		for (STPO stpo :otherSTPOS) {
			
			if (stpo.getIDNRK().equalsIgnoreCase(selectedName))
				continue;
			
			List<MAST> masts = cdposService.getMASTfromSTLNR(stpo.getStpoIdentity().getSTLNR());
			MiscIdentity identity = new MiscIdentity();
			identity.setSTLKN(stpo.getStpoIdentity().getSTLKN());
			identity.setSTLNR(stpo.getStpoIdentity().getSTLNR());
			identity.setSTPOZ(stpo.getStpoIdentity().getSTPOZ());
			List<MISCELLANEOUS> miscs = cdposService.getMiscsWithId(identity);
			
			for (MAST mast : masts) {

				for (MISCELLANEOUS misc : miscs) {

					String vol = Integer.toString((int) (Math.random() * 10));
					String avl = Integer.toString(misc.STOCK - (int) (Math.random() * 10));
					
					String pGroup = misc.PURCHASING_GROUP.equals("")?"0":misc.PURCHASING_GROUP;
					String mGroup = misc.MRP_GROUP.equals("")?"0":misc.MRP_GROUP;
					
					writer.write(misc.getIdentity().toString() + "_" + mast.getIdentity().toString() + ","
							+ misc.WERKS + "," + misc.STORAGE_LOCATION + "," + misc.MATERIAL_GROUP + "," + vol
							+ "," + pGroup + "," + mGroup + "," + mast.LOSBS + "," + misc.STOCK + ","
							+ avl);
					writer.newLine();
				}
				break;
			}
			
		}
 
		writer.close();

		ProcessBuilder builder = new ProcessBuilder("sh",
				"/home/mukul/git/BOMRecommendation/machine_learning/execute.sh");
		Process p = builder.start();
		p.waitFor();

		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = br.readLine();
		List<STPO> list = new ArrayList<STPO>();
		HashSet<String> set = new HashSet<String>();

		while ((line = br.readLine()) != null) {

			String parts[] = line.split(",")[0].split("_");
			StpoIdentity id = new StpoIdentity();
			id.setSTLKN(parts[0]);
			id.setSTLNR(parts[1]);
			id.setSTPOZ(parts[2]);

			STPO stpo = cdposService.getSTPOById(id);
			if (set.contains(stpo.getIDNRK()))
				continue;
			
			set.add(stpo.getIDNRK());
			list.add(stpo);
			if (list.size() == 9)
				break;
		}

		bomSearchForm.setResult(list);
		ModelAndView mv = new ModelAndView("welcome", "bomSearchForm", bomSearchForm);
		return mv;
	}

}
