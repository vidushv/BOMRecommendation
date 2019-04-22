package com.cornell.se.bom.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cornell.se.bom.forms.BOMSearchForm;
import com.cornell.se.bom.model.STPO;


@Controller
public class WelcomeController {

	@GetMapping("/")
    public ModelAndView displayIndexPage() {
		BOMSearchForm bomSearchForm = new BOMSearchForm();
		ModelAndView  mv = new ModelAndView("welcome","bomSearchForm",bomSearchForm);
		return mv;
    }
	
	@PostMapping("/welcome")
    public ModelAndView displaySearchPage(@RequestParam(value="selectedIdentity", required=true) String selectedIdentity, @RequestParam(value="selectedIDNRK", required=true) String selectedName) {
		
		System.out.println(selectedIdentity);
		BOMSearchForm bomSearchForm = new BOMSearchForm();
		bomSearchForm.setSelectedIDNRK(selectedName);
		bomSearchForm.setLoadedByPost(true);
		
		//CALL ML from service
		
		List<STPO> list = new ArrayList<STPO>();
		STPO stpo1 = new STPO();
		stpo1.setIDNRK("Recommendation 1");
		STPO stpo2 = new STPO();
		stpo2.setIDNRK("Recommendation 2");
		list.add(stpo1);
		list.add(stpo2);
		
		bomSearchForm.setResult(list);
		
		ModelAndView  mv = new ModelAndView("welcome","bomSearchForm",bomSearchForm);
		return mv;
    }
}
