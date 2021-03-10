package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Brewery;
import com.techelevator.model.User;

public interface BreweryDAO
{
	List<Brewery> findAll();
	
	Brewery getById(int id);
	
	Brewery getByName(String name);
	
	int create(String name, User brewer);
	
	Brewery update(Brewery breweryToUpdate);
	
}
