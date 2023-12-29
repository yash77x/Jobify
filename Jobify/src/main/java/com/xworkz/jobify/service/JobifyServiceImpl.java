package com.xworkz.jobify.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.xworkz.jobify.dto.JobifyDTO;
import com.xworkz.jobify.dto.JobifyEntity;
import com.xworkz.jobify.repo.JobifyRepositoryImpl;
import com.xworkz.jobify.utils.EmailUtil;

@Service
public class JobifyServiceImpl implements JobifyService {

	@Autowired
	private JobifyRepositoryImpl repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

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
			if (isExist(dto.getEmail(), model)) {
				isValid = false;
			}
		}

		if (isValid == true) {

			String encryptedPassword = passwordEncoder.encode(dto.getPassword());
			dto.setPassword(encryptedPassword);

			JobifyEntity entity = new JobifyEntity();
			entity.setCreatedBy(dto.getEmail());
			entity.setCreatedOn(LocalDate.now());
			entity.setCount(0);
			entity.setStatus("Open");
			BeanUtils.copyProperties(dto, entity);
			EmailUtil utils = new EmailUtil();
			utils.mail(dto.getEmail(), dto.getName());
			return repo.save(entity);
		}

		return isValid;
	}

	@Override
	public List<JobifyEntity> readAll() {
		return repo.readAll();

	}

	@Override
	public JobifyEntity findByEmail(String email) {
		if (email != null && !email.isEmpty()) {
			return repo.findByEmail(email);
		}
		return null;
	}


	@Override
	public boolean isExist(String email, Model model) {
		JobifyEntity found = repo.findByEmail(email);
		if (found != null) {
			if (found.getEmail().equals(email)) {
				model.addAttribute("emailExist", "Email Already Exists.");
				return true;
			}
			return false;
		}
		return false;
	}
	
	@Override
	@Transactional
	public JobifyEntity login(String email, String password, Model model) {
	    if (email != null && !email.isEmpty()) {
	        JobifyEntity entity = repo.findByEmail(email);
	        if (entity != null && entity.getStatus().equals("Open")) {
	            if (entity.getCount() < 3) {
	                String storedEncodedPassword = entity.getPassword();
	                if (passwordEncoder.matches(password, storedEncodedPassword)) {
	                    return entity;
	                } else {
	                	model.addAttribute("wrong", "Wrong Password, Try Again");
	                    entity.setCount(entity.getCount() + 1);
	                    if (entity.getCount() == 3) {
	                        entity.setStatus("Blocked");
	                    }
	                    repo.save(entity);
	                    return null;
	                }
	            } else {
	                entity.setStatus("Blocked");
	                repo.save(entity);
	                return null;
	            }
	        }
        	model.addAttribute("block", "Your account has been blocked. Plese click on forgot password to change");

	    }
	    return null;
	}

}
