package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Image;

@Service
public class ImageSqlDAO implements ImageDAO
{

	private JdbcTemplate jdbcTemplate;
	
	public ImageSqlDAO(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Image> getAllByBreweryId(int id)
	{
		List<Image> images = new ArrayList<>();
		String sql = "SELECT * "
				   + "FROM images "
				   + "WHERE brewery_id = ?;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		while(results.next())
		{
			Image image = mapRowToImage(results);
			images.add(image);
		}
		return images;
	}

	@Override
	public Image getByBeerId(int id)
	{
		Image image = new Image();
		String sql = "SELECT * "
				   + "FROM images "
				   + "WHERE beer_id = ?;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		if(results.next())
		{
			image = mapRowToImage(results);
		}
		else
		{
			throw new RuntimeException("beerId " + id + " was not found.");
		}
		return image;
	}

	@Override
	public Image getByUrl(String url)
	{
		Image image = new Image();
		String sql = "SELECT * "
				   + "FROM images "
				   + "WHERE url = ?;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, url);
		if(results.next())
		{
			image = mapRowToImage(results);
		}
		else
		{
			throw new RuntimeException("image URL " + url + " was not found.");
		}
		return image;
	}

	@Override
	public Image getById(int id)
	{
		Image image = new Image();
		String sql = "SELECT * "
				   + "FROM images "
				   + "WHERE image_id = ?;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		if(results.next())
		{
			image = mapRowToImage(results);
		}
		else
		{
			throw new RuntimeException("imageId " + id + " was not found.");
		}
		return image;
	}

	@Override
	public List<Image> getByDescription(String description)
	{
		List<Image> images = new ArrayList<>();
		String sql = "SELECT * "
				   + "FROM images "
				   + "WHERE description ILIKE ?;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + description + "%");
		while(results.next())
		{
			Image image = mapRowToImage(results);
			images.add(image);
		}
		return images;
	}

	@Override
	public Image add(Image image)
	{
		int imageId = getNextId();
		Image newImage = new Image();
		String sql = "INSERT INTO images "
				   + "(image_id, url, description, "
				   + "alt_text, beer_id, brewery_id) "
				   + "values(?, ?, ?, ?, ?, ?);";
		
		jdbcTemplate.update(sql,
						    imageId,
						    image.getUrl(),
						    image.getDescription(),
						    image.getAltText(),
						    image.getBeerId(),
						    image.getBreweryId());
		image.setId(imageId);
		return image;
	}

	@Override
	public Image update(Image image)
	{
		String sql = "UPDATE images "
				   + "SET url = ?, "
				   + "description = ?, "
				   + "alt_text = ?, "
				   + "beer_id = ?, "
				   + "brewery_id = ? "
				   + "WHERE image_id = ?;";
		
		jdbcTemplate.update(sql,
							image.getUrl(),
							image.getDescription(),
							image.getAltText(),
							image.getBeerId() > 0 ? image.getBeerId() : null,
							image.getBreweryId() > 0 ? image.getBreweryId() : null,
							image.getId());
		
		return getById(image.getId());
	}

	@Override
	public boolean delete(int id)
	{
		String sql = "DELETE FROM images WHERE image_id = ?;";
		
		int success = jdbcTemplate.update(sql, id);
		
		if(success > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
		
	private Image mapRowToImage(SqlRowSet rs)
	{
		Image image = new Image();
		image.setId(rs.getInt("image_id"));
		image.setUrl(rs.getString("url"));
		image.setDescription(rs.getString("description"));
		image.setAltText(rs.getString("alt_text"));
		int nullCheck = rs.getInt("beer_id");
		image.setBeerId(rs.wasNull() ? -1 : rs.getInt("beer_id"));
		nullCheck = rs.getInt("brewery_id");
		image.setBreweryId(rs.wasNull() ? -1 : rs.getInt("brewery_id"));

		return image;
	}
	
	private int getNextId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('images_image_id_seq') AS next_id");
		if(nextIdResult.next()) {
			return nextIdResult.getInt("next_id");
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new image");
		}
	}

	

}
