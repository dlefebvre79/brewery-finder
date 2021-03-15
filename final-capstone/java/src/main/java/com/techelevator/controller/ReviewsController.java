package com.techelevator.controller;

import java.util.List;

import javax.management.relation.RelationNotFoundException;
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

import com.techelevator.dao.ReviewsDAO;
import com.techelevator.model.Beer;
import com.techelevator.model.Reviews;
import com.techelevator.model.exceptions.BeerAlreadyExistsException;
import com.techelevator.model.exceptions.BeerNotFoundException;
import com.techelevator.model.exceptions.ReviewAlreadyExistsException;
import com.techelevator.model.exceptions.ReviewNotFoundException;



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
		if(reviewsDAO.getById(review.getBeerId()) != null)
				{
				throw new ReviewAlreadyExistsException();
				}
		}
		catch (ReviewNotFoundException e)
		{
			int reviewId = reviewsDAO.create(review);
			newReview = reviewsDAO.getById(reviewId);
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
