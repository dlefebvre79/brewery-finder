package com.techelevator.model;

import java.math.BigDecimal;

public class Beer
{
	private int id;
	private String name;
	private String info;
	private BigDecimal abv;
	private int ibu;
	private String type;
	private int breweryId;
	private boolean active;
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
	public BigDecimal getAbv()
	{
		return abv;
	}
	public void setAbv(BigDecimal abv)
	{
		this.abv = abv;
	}
	public int getIbu()
	{
		return ibu;
	}
	public void setIbu(int ibu)
	{
		this.ibu = ibu;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getInfo()
	{
		return info;
	}
	public void setInfo(String info)
	{
		this.info = info;
	}
	public int getBreweryId()
	{
		return breweryId;
	}
	public void setBreweryId(int breweryId)
	{
		this.breweryId = breweryId;
	}
//	public Image getImage()
//	{
//		return image;
//	}
//	public void setImage(Image image)
//	{
//		this.image = image;
//	}
	public boolean isActive()
	{
		return active;
	}
	public void setActive(boolean active)
	{
		this.active = active;
	}
	
	
}
