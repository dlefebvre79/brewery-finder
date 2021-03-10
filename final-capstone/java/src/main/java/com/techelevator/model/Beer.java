package com.techelevator.model;

import java.math.BigDecimal;

public class Beer
{
	private int id;
	private String name;
	private String description;
	private BigDecimal abv;
	private int ibu;
	private Image image;
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
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
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
	public Image getImage()
	{
		return image;
	}
	public void setImage(Image image)
	{
		this.image = image;
	}
	public boolean isActive()
	{
		return active;
	}
	public void setActive(boolean active)
	{
		this.active = active;
	}
	
	
}
