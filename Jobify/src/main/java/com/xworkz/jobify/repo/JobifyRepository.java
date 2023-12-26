package com.xworkz.jobify.repo;

import com.xworkz.jobify.dto.JobifyEntity;

public interface JobifyRepository {
	
	public boolean save(JobifyEntity dto);

}
