package com.techelevator.model;

public class Image
{
	private int id;
	private String url;
	private String description;
	private String altText;
	private int beerId = -1;
	private int breweryId = -1;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getAltText()
	{
		return altText;
	}

	public void setAltText(String altText)
	{
		this.altText = altText;
	}

	public int getBeerId()
	{
		return beerId;
	}

	public void setBeerId(int beerId)
	{
		this.beerId = beerId;
	}

	public int getBreweryId()
	{
		return breweryId;
	}

	public void setBreweryId(int breweryId)
	{
		this.breweryId = breweryId;
	}

}
