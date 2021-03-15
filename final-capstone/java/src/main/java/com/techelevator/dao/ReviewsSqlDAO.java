package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Beer;
import com.techelevator.model.Reviews;

@Service
public class ReviewsSqlDAO implements ReviewsDAO 
{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserDAO userDAO;
	
	public ReviewsSqlDAO(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Reviews> findAll()
	{
		List<Reviews> reviews = new ArrayList<>();
		String sql = "SELECT * FROM reviews";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next())
		{
			Reviews review = mapRowToReviews(results);
			reviews.add(review);
		}
		
		return reviews;
	}
	
	@Override
	public Reviews getById(int id)
	{
		String sql = "SELECT * FROM reviews WHERE review_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		if(results.next())
		{
			return mapRowToReviews(results);
		}
		else
		{
			throw new RuntimeException("reviewId " + id + " was not found.");
		}
	}
	
	@Override
	public List<Reviews> getByBeerId(int id)
	{
		List<Reviews> reviews = new ArrayList<>();
		String sql = "SELECT * FROM reviews WHERE beer_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		while(results.next())
		{
			Reviews review = mapRowToReviews(results);
			reviews.add(review);
		}
		
		return reviews;
	}
	
	@Override
	public int create(Reviews review)
	{
		int beerId = getNextId();
		String sql = "INSERT INTO reviews "
				   + "(review_id, beer_id, user_id, subject_title, review, rating, create_date) "
				   + "values(?, ?, ?, ?, ?, ?, ?);";
		
		jdbcTemplate.update(sql,
							review.getId(),
							review.getBeerId(),
							review.getUserId(),
							review.getSubjectTitle(),
							review.getReview(),
							review.getRating(),
							review.getCreateDate());
		return beerId;
	}
	
	
	private Reviews mapRowToReviews(SqlRowSet rows)
	{
		String result;
		Reviews reviews = new Reviews();
		reviews.setId(rows.getInt("review_id"));
		reviews.setBeerId(rows.getInt("beer_id"));
		reviews.setUserId(rows.getInt("user_id"));
		reviews.setSubjectTitle(rows.getString("subject_title"));
		reviews.setReview(rows.getString("review"));
		reviews.setRating(rows.getInt("rating"));
		reviews.setCreateDate(rows.getTimestamp("create_date"));
		
		return reviews;
	}
	
	private int getNextId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('reviews_review_id_seq') AS next_id");
		if(nextIdResult.next()) {
			return nextIdResult.getInt("next_id");
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new review");
		}
	}
	
}
