package com.techelevator.dao;

import java.math.BigDecimal;
import java.util.List;

import com.techelevator.model.Beer;

public interface BeerDAO 
{
	List<Beer> findAll();
	
	Beer getById(int id);
	
	List<Beer> getByName(String name);
	
	int create(Beer beer);
	
	Beer update(Beer beerToUpdate);
	
	boolean deleteBeerById(int id);
	
	List<Beer> getByBreweryId(int id);
}
