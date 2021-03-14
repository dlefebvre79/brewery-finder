package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Image;

public interface ImageDAO
{
	List<Image> getAllByBreweryId(int id);
	
	Image getByBeerId(int id);
	
	Image getById(int id);
	
	Image getByUrl(String url);
	
	List<Image> getByDescription(String description);
	
	Image add(Image image);
	
	Image update(Image image);
	
	boolean delete(int id);
}
