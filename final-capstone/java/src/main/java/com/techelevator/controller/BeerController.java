package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.BeerDAO;
import com.techelevator.model.Beer;
import com.techelevator.model.Brewery;
import com.techelevator.model.exceptions.BeerAlreadyExistsException;
import com.techelevator.model.exceptions.BeerNotFoundException;

@RestController
@CrossOrigin
@RequestMapping(path = "/beer")

public class BeerController {

	@Autowired
	private BeerDAO beerDAO;
	
	@PreAuthorize("hasRole('ROLE_BREWER')")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Beer create(@Valid @RequestBody Beer beer)
	{
		Beer newBeer = null;
		try 
		{
		if(beerDAO.getByName(beer.getName()).size() > 0)
				{
				throw new BeerAlreadyExistsException();
				}
		}
		catch (BeerNotFoundException e)
		{
			int beerId = beerDAO.create(beer);
			newBeer = beerDAO.getById(beerId);
		}
		return newBeer;
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public List<Beer> findAll()
	{
		return beerDAO.findAll();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Beer getById(@Valid @PathVariable int id)
	{
		return beerDAO.getById(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/brewery/{id}", method = RequestMethod.GET)
	public List<Beer> getByBreweryId(@Valid @PathVariable int id)
	{
		return beerDAO.getByBreweryId(id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public List<Beer> getByName(@Valid @PathVariable String name)
	{
		return beerDAO.getByName(name);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/delete-beer/{id}", method = RequestMethod.DELETE)
	public int deleteBeerById(@Valid @PathVariable int id)
	{
		return beerDAO.deleteBeerById(id);
	}
	
	
	
	
	
	
}
