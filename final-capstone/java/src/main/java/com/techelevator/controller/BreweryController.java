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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.BreweryDAO;
import com.techelevator.model.Brewery;
import com.techelevator.model.User;
import com.techelevator.model.exceptions.BreweryAlreadyExistsException;
import com.techelevator.model.exceptions.BreweryNotFoundException;

@RestController
@CrossOrigin
@RequestMapping(path = "/brewery")
public class BreweryController
{
	@Autowired
	private BreweryDAO breweryDAO;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Brewery create(@Valid @RequestBody Brewery brewery)
	{
		Brewery newBrewery = null;
		try
		{
			if(breweryDAO.getByName(brewery.getName()).size() > 0)
			{
				throw new BreweryAlreadyExistsException();	
			}
			
		}
		catch (BreweryNotFoundException e)
		{
			int breweryId = breweryDAO.create(brewery.getName(), brewery.getBrewer());
			newBrewery = breweryDAO.getById(breweryId);
		}
		return newBrewery;
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public List<Brewery> findAll(@RequestParam(required = false) Integer brewerId )
	{
		if(brewerId != null)
		{
			return breweryDAO.findAll(brewerId);
		}
		else
		{
			return breweryDAO.findAll();
		}
		
	}

//	@ResponseStatus(HttpStatus.OK)
//	@RequestMapping(method = RequestMethod.GET)
//	public List<Brewery> getByBrewer(@Valid @RequestParam int brewerId)
//	{
//		return breweryDAO.getByBrewerId(brewerId);
//	}

	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Brewery getById(@Valid @PathVariable int id)
	{
		return breweryDAO.getById(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean deleteById(@Valid @PathVariable int id)
	{
		return breweryDAO.deleteById(id);
	}

	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public List<Brewery> getByName(@Valid @PathVariable String name)
	{
		return breweryDAO.getByName(name);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_BREWER', 'ROLE_ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Brewery update(@Valid @RequestBody Brewery brewery)
	{
		return breweryDAO.update(brewery);

	}

}
