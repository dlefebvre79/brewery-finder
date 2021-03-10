package com.techelevator.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.BreweryDAO;
import com.techelevator.model.Brewery;
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
//		try
//		{
//			newBrewery = breweryDAO.getByName(brewery.getName());
//			throw new BreweryAlreadyExistsException();
//		}
//		catch (BreweryNotFoundException e)
//		{
			int breweryId = breweryDAO.create(brewery.getName(), brewery.getBrewer());
			System.out.println(breweryId);
			newBrewery = breweryDAO.getById(breweryId);
//		}
		return newBrewery;
	}

}
