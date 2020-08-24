package com.enit.servingservice.repository;

import java.util.List;

import com.enit.servingservice.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecRepository extends JpaRepository<Ad, String> {

	/**
	 * Method to fetch the employee details on the basis of designation by using
	 * Elastic-Search-Repository.
	 * 
	 * @param designation
	 * @return
	 */
	
     public List<Ad> findByRequestId(String id);
 
}