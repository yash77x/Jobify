package com.xworkz.jobify.service;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.xworkz.jobify.dto.JobifyDTO;
import com.xworkz.jobify.dto.JobifyEntity;
import com.xworkz.jobify.repo.JobifyRepository;
import com.xworkz.jobify.repo.JobifyRepositoryImpl;

@Service
public class JobifyServiceImpl implements JobifyService {

	@Autowired
	private JobifyRepositoryImpl repo;

	@Override
	public boolean ValidateAndSave(JobifyDTO dto, Model model) {
		boolean isValid = true;
		if (dto != null) {
			if (dto.getName() == null || dto.getName().isEmpty() || dto.getName().length() <= 3) {
				model.addAttribute("nameInvalid", "Please Enter Correct Name");
				isValid = false;
			}
			if (dto.getEmail() == null || dto.getEmail().isEmpty()
					|| !dto.getEmail().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
				model.addAttribute("emailInvalid", "Please Enter Correct Email");
				isValid = false;
			}
			if (!dto.getMobile().matches("^[6-9]\\d{9}$")) {
				model.addAttribute("mobileInvalid", "Please Enter Correct Mobile no.");
				isValid = false;
			}
			if (!dto.getPassword().matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")) {
				model.addAttribute("passwordInvalid",
						"Please Enter Password having Min 8 characters, one uppercase letter, one lowercase letter, one number and one special character:");
				isValid = false;
			}
			if (!dto.getConfirmPass().equals(dto.getPassword())) {
				model.addAttribute("conPasswordInvalid", "Password Does not Match");
				isValid = false;
			}

		}

		if (isValid == true) {

			JobifyEntity entity = new JobifyEntity();
			entity.setCreatedBy(dto.getEmail());
			entity.setCreatedOn(LocalDate.now());

			BeanUtils.copyProperties(dto, entity);
			return repo.save(entity);
		}

		return isValid;
	}
}
