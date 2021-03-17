package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Reviews;



public interface ReviewsDAO 
{

	List<Reviews> findAll();
	
	Reviews getById(int id);
	
	List<Reviews> getByBeerId(int id);
	
	Reviews create(Reviews review);
	
}
