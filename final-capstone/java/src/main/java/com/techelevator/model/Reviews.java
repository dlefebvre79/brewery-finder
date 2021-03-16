package com.techelevator.model;

import java.sql.Timestamp;

public class Reviews 
{
	private int id;
	private int beerId;
	private String beerName;
	private int userId;
	private String subjectTitle;
	private String review;
	private int rating;
	private Timestamp createDate;
	
	
	public int getId() 
	{
		return id;
	}
	public void setId(int id)
	{
		 this.id = id;
	}
	public int getBeerId()
	{
		return beerId;
	}
	public void setBeerId(int beerId)
	{
		this.beerId = beerId;
	}
	public String getBeerName()
	{
		return beerName;
	}
	public void setBeerName(String beerName)
	{
		this.beerName = beerName;
	}
	public int getUserId()
	{
		return userId;
	}
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	public String getSubjectTitle()
	{
		return subjectTitle;
	}
	public void setSubjectTitle(String subjectTitle)
	{
		this.subjectTitle = subjectTitle;
	}
	public String getReview()
	{
		return review;
	}
	public void setReview(String review)
	{
		this.review = review;
	}
	public int getRating()
	{
		return rating;
	}
	public void setRating(int rating)
	{
		this.rating = rating;
	}
	public Timestamp getCreateDate()
	{
		return createDate;
	}
	public void setCreateDate(Timestamp createDate)
	{
		this.createDate = createDate;
	}

}
