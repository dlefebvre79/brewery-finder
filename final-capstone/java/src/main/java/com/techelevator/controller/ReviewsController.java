package com.techelevator.controller;

import java.util.List;

import javax.management.relation.RelationNotFoundException;
import javax.validation.Valid;

import com.techelevator.model.exceptions.*;
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

import com.techelevator.dao.ReviewsDAO;
import com.techelevator.model.Beer;
import com.techelevator.model.Reviews;


@RestController
@CrossOrigin
@RequestMapping(path = "/reviews")
public class ReviewsController 
{
	@Autowired
	private ReviewsDAO reviewsDAO;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Reviews create(@Valid @RequestBody Reviews review)
	{
		Reviews newReview = null;
		try 
		{
			newReview = reviewsDAO.create(review);
		}
		catch (Exception e)
		{
			throw new ReviewNotCreatedException();
		}

		return newReview;
	}

	///@PreAuthorize ("hasRole('ROLE_USER')")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public List<Reviews> findAll()
	{
		return reviewsDAO.findAll();
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Reviews getById(@Valid @PathVariable int id)
	{
		return reviewsDAO.getById(id);
	}
	
	//@PreAuthorize ("hasRole('ROLE_BREWER')")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/beer/{id}", method = RequestMethod.GET)
	public List<Reviews> getByBeerId(@Valid @PathVariable int id)
	{
		return reviewsDAO.getByBeerId(id);
	}
	
	
	
}
