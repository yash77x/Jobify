package com.xworkz.jobify.service;

import org.springframework.ui.Model;

import com.xworkz.jobify.dto.JobifyDTO;

public interface JobifyService {
	
	public boolean ValidateAndSave(JobifyDTO dto, Model model);

}
