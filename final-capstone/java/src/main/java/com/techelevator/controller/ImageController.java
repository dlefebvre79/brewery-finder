package com.techelevator.controller;

import java.util.ArrayList;
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

import com.techelevator.dao.ImageDAO;
import com.techelevator.model.Image;
import com.techelevator.model.exceptions.ImageAlreadyExistsException;
import com.techelevator.model.exceptions.ImageNotFoundException;

@RestController
@CrossOrigin
@RequestMapping(path = "/image")
public class ImageController
{
	@Autowired
	private ImageDAO imageDAO;
	
	@PreAuthorize("isAuthenticated()")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Image add(@Valid @RequestBody Image image)
	{
		Image newImage = null;
		try
		{
			if(imageDAO.getByUrl(image.getUrl()) != null)
			{
				throw new ImageAlreadyExistsException();
			}
		}
		catch (ImageNotFoundException e)
		{
			newImage = imageDAO.add(image);
		}
		
		return newImage;
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/brewery/{breweryId}", method = RequestMethod.GET)
	public List<Image> getByBreweryId(@Valid @PathVariable int breweryId)
	{
		return imageDAO.getAllByBreweryId(breweryId);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/beer/{beerId}", method = RequestMethod.GET)
	public Image getByBeerId(@Valid @PathVariable int beerId)
	{
		return imageDAO.getByBeerId(beerId);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public List<Image> getByParam(@Valid @RequestParam(required = false) String description, @RequestParam(required = false) String url)
	{
		List<Image> images = new ArrayList<>();
		
		if(description != null)
		{
			images.addAll(imageDAO.getByDescription(description));
		}
		if (url != null)
		{
			images.add(imageDAO.getByUrl(url));
		}
		
		return images;
	}
	
	@PreAuthorize("isAuthenticated()")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Image update(@Valid @RequestBody Image imageToUpdate)
	{
		Image image = null;
		try
		{
			image = imageDAO.update(imageToUpdate);
		}
		catch (Exception e)
		{
			
		}
		
		return image;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BREWER')")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public boolean delete(@Valid @PathVariable int id)
	{
		return imageDAO.delete(id);
	}
	
	
	
}
