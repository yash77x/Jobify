package com.xworkz.jobify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xworkz.jobify.dto.JobifyDTO;
import com.xworkz.jobify.service.JobifyService;
import com.xworkz.jobify.service.JobifyServiceImpl;

@Controller
public class JobifyController {

	@Autowired
	private JobifyServiceImpl service;

	public JobifyController() {
		System.out.println("Jobify controller created");
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(JobifyDTO dto, Model model) {
		boolean saved = service.ValidateAndSave(dto, model);
		if (saved) {
			model.addAttribute("saved", "Registered Successfully");
			return "Register";
		}
		return "Register";
	}
}
