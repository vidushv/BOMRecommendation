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
import com.cornell.se.bom.recommendors.RecommendationFactory;
import com.cornell.se.bom.service.CDPOSService;

@Controller
public class WelcomeController {

	@Autowired
	CDPOSService cdposService;
	
	@Autowired
	RecommendationFactory recommendationFactory;
	
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
		
		BOMSearchForm bomSearchForm = new BOMSearchForm();
		bomSearchForm.setSelectedIDNRK(selectedName);
		bomSearchForm.setLoadedByPost(true);
		bomSearchForm.setSelectedIdentity(selectedIdentity);
		bomSearchForm.setMATKL(MATKL);
		
		StpoIdentity identity  = new StpoIdentity();
		identity.setSTLKN(SelectedStlkn);
		identity.setSTLNR(SelectedStlnr);
		identity.setSTPOZ(SelectedStpoz);
		
		STPO fromUI = cdposService.getSTPOById(identity);

		List<STPO> result = recommendationFactory.getRecommendor("Random Forest").getRecommendations(fromUI, cdposService);
		
		bomSearchForm.setResult(result);
		ModelAndView mv = new ModelAndView("welcome", "bomSearchForm", bomSearchForm);
		return mv;
	}

}
