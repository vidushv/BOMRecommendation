package com.cornell.se.bom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
