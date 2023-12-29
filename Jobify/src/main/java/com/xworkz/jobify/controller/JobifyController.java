package com.xworkz.jobify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.jobify.dto.JobifyDTO;
import com.xworkz.jobify.dto.JobifyEntity;
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
		if (saved == true) {
			model.addAttribute("saved", "Registered Successfully");
			return "Register";
		}
		return "Register";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam String email, @RequestParam String password, Model model) {
		JobifyEntity entity = service.login(email, password, model);
		if (entity != null) {
			if (entity.getAccount().equals("JobSeeker")) {
				return "Jobseeker";
			}
			return "Jobprovider";
		}

		return "Login";
	}
}