package com.xworkz.jobify.repo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

import com.xworkz.jobify.dto.JobifyEntity;

@Repository
public class JobifyRepositoryImpl implements JobifyRepository {
	
	@PersistenceUnit
	private EntityManagerFactory emf;

	@Override
	public boolean save(JobifyEntity dto) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		try {
			transaction.begin();
			em.persist(dto);
			transaction.commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		
		
		return false;
	}

}
