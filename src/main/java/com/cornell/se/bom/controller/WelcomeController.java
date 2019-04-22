package com.cornell.se.bom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cornell.se.bom.forms.BOMSearchForm;


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
		ModelAndView  mv = new ModelAndView("welcome","bomSearchForm",bomSearchForm);
		return mv;
    }
}
