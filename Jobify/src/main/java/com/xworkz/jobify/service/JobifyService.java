package com.xworkz.jobify.service;

import java.util.List;

import org.springframework.ui.Model;

import com.xworkz.jobify.dto.JobifyDTO;
import com.xworkz.jobify.dto.JobifyEntity;

public interface JobifyService {
	
	public boolean ValidateAndSave(JobifyDTO dto, Model model);

	public List<JobifyEntity> readAll();
	
	public JobifyEntity findByEmail(String email);

	public boolean isExist(String email, Model model);


}
