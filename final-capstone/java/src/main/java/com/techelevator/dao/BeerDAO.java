package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Beer;

public interface BeerDAO 
{
	List<Beer> findAll();
	
	Beer getById(int id);
	
	List<Beer> getByName(String name);
	
	int create(String name, int breweryId);
	
	Beer update(Beer beerToUpdate);
	
	List<Beer> getByBreweryId(int id);
}
