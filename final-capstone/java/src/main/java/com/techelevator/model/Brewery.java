package com.techelevator.model;

import java.util.List;

public class Brewery
{
	private int id;
	private String name;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;
	private String[] daysOpen;
	private String[] hours;
	private String history;
	private String atmosphere;
	private List<Image> images;
	private List<Beer> beers;
	private boolean familyFriendly;
	private boolean patio;
	private boolean food;
	private boolean active;
	private User brewer;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getZip()
	{
		return zip;
	}

	public void setZip(String zip)
	{
		this.zip = zip;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String[] getDaysOpen()
	{
		return daysOpen;
	}

	public void setDaysOpen(String[] daysOpen)
	{
		this.daysOpen = daysOpen;
	}

	public String[] getHours()
	{
		return hours;
	}

	public void setHours(String[] hours)
	{
		this.hours = hours;
	}

	public String getHistory()
	{
		return history;
	}

	public void setHistory(String history)
	{
		this.history = history;
	}

	public String getAtmosphere()
	{
		return atmosphere;
	}

	public void setAtmosphere(String atmosphere)
	{
		this.atmosphere = atmosphere;
	}

	public List<Image> getImages()
	{
		return images;
	}

	public void setImages(List<Image> images)
	{
		this.images = images;
	}

	public List<Beer> getBeers()
	{
		return beers;
	}

	public void setBeers(List<Beer> beers)
	{
		this.beers = beers;
	}

	public boolean isFamilyFriendly()
	{
		return familyFriendly;
	}

	public void setFamilyFriendly(boolean familyFriendly)
	{
		this.familyFriendly = familyFriendly;
	}

	public boolean isPatio()
	{
		return patio;
	}

	public void setPatio(boolean patio)
	{
		this.patio = patio;
	}

	public boolean isFood()
	{
		return food;
	}

	public void setFood(boolean food)
	{
		this.food = food;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public User getBrewer()
	{
		return brewer;
	}
	
	public void setBrewer(User brewer)
	{
		this.brewer = brewer;
	}
}
