package com.cornell.se.bom.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

	@GetMapping("/")
	public ModelAndView displayIndexPage() {
		BOMSearchForm bomSearchForm = new BOMSearchForm();
		ModelAndView mv = new ModelAndView("welcome", "bomSearchForm", bomSearchForm);
		return mv;
	}

	@PostMapping("/welcome")
	public ModelAndView displaySearchPage(
			@RequestParam(value = "selectedIdentity", required = true) String selectedIdentity,
			@RequestParam(value = "selectedIDNRK", required = true) String selectedName) throws IOException, InterruptedException {

		System.out.println(selectedIdentity);
		BOMSearchForm bomSearchForm = new BOMSearchForm();
		bomSearchForm.setSelectedIDNRK(selectedName);
		bomSearchForm.setLoadedByPost(true);
		bomSearchForm.setSelectedIdentity(selectedIdentity);

		String stlnr = selectedIdentity.substring(selectedIdentity.indexOf("STLNR") + 8,
				selectedIdentity.indexOf("STLKN") - 3);
		List<MAST> masts = cdposService.getMASTfromSTLNR(stlnr);

		String stlkn = selectedIdentity.substring(selectedIdentity.indexOf("STLKN") + 8,
				selectedIdentity.indexOf("STPOZ") - 3);
		String stpoz = selectedIdentity.substring(selectedIdentity.indexOf("STPOZ") + 8, selectedIdentity.length() - 2);
		
		MiscIdentity identity = new MiscIdentity();
		identity.setSTLKN(stlkn);
		identity.setSTLNR(stlnr);
		identity.setSTPOZ(stpoz);
		
		List<MISCELLANEOUS> miscs = cdposService.getMiscsWithId(identity);
		
		System.out.println(miscs);
		System.out.println(masts);
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("/home/mukul/git/BOMRecommendation/machine_learning/csvfile.csv"));
		
		writer.write("ID,Plant,Storage Location,Material Group,Volume,Purchasing Group,MRP Group,Lot Size,Stock,Availbility");
		writer.newLine();
		
		for (MAST mast : masts) {
			
			for (MISCELLANEOUS misc : miscs) {
				
				String vol = Integer.toString((int)Math.random()*10);
				String avl = Integer.toString(misc.STOCK - (int)Math.random()*10);
				writer.write(
				misc.getIdentity().toString()+"_"+mast.getIdentity().toString()+","
				+mast.getIdentity().WERKS+","
				+misc.STORAGE_LOCATION+","
				+misc.MATERIAL_GROUP+","
				+vol+","
				+misc.PURCHASING_GROUP+","
				+misc.MRP_GROUP+","
				+mast.LOSBS+","
				+misc.STOCK+","
				+avl);
				writer.newLine();
			}
			
		}
		
		writer.close();
		
		/*
		 *  Plant - MAST (STLNR)
			Storage Location - MISC
			Material Group - MISC
			Volume- MISC
			Purchasing - MISC
			Group,
			MRP Group - MISC
			Lot Size - MAST
			Stock - MISC
			Availbility - MISC
		 * */
		
		
		// CALL ML from service
		
		ProcessBuilder builder = new ProcessBuilder("sh","/home/mukul/git/BOMRecommendation/machine_learning/execute.sh");
		Process p = builder.start();
		p.waitFor();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = br.readLine();
		List<STPO> list = new ArrayList<STPO>();
		
		while ((line = br.readLine()) != null) {
			
			System.out.println(line);
			String parts []  = line.split(",")[0].split("_");
			StpoIdentity id = new StpoIdentity();
			id.setSTLKN(parts[0]);
			id.setSTLNR(parts[1]);
			id.setSTPOZ(parts[2]);
			
			System.out.println(id);
			STPO stpo = cdposService.getSTPOById(id);			
			list.add(stpo);
        }

		bomSearchForm.setResult(list);

		ModelAndView mv = new ModelAndView("welcome", "bomSearchForm", bomSearchForm);
		return mv;
	}


}
