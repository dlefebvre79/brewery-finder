package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Brewery;
import com.techelevator.model.User;
import com.techelevator.model.exceptions.BreweryNotFoundException;

@Service
public class BrewerySqlDAO implements BreweryDAO
{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserDAO userDAO;

	public BrewerySqlDAO(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	
	@Override
	public List<Brewery> findAll()
	{
		List<Brewery> breweries = new ArrayList<>();
		String sql = "SELECT * FROM breweries";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next())
		{
			Brewery brewery = mapRowToBrewery(results);
			breweries.add(brewery);
		}
		
		return breweries;
	}

	@Override
	public Brewery getById(int id)
	{
		String sql = "SELECT * FROM breweries WHERE brewery_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		if(results.next())
		{
			return mapRowToBrewery(results);
		}
		else
		{
			throw new RuntimeException("breweryId " + id + " was not found.");
		}
	}

	@Override
	public Brewery getByName(String name)
	{
		for (Brewery brewery : this.findAll())
		{
			if (brewery.getName().toLowerCase().equals(name.toLowerCase()))
			{
				return brewery;
			}
		}
		throw new BreweryNotFoundException();
	}

	@Override
	public int create(String name, User brewer)
	{
		int breweryId = getNextId();
		String sql = "INSERT INTO breweries "
				   + "(brewery_id, name, user_id) "
				   + "values(?, ?, ?);";
		
		jdbcTemplate.update(sql,
							breweryId,
							name,
							brewer.getId());
		return breweryId;
	}

	@Override
	public Brewery update(Brewery breweryToUpdate)
	{
		
		String sql = "UPDATE breweries "
				   + "SET name = ?, "
				   + "address = ?, "
				   + "city = ?, "
				   + "zipcode = ?, "
				   + "phone_number = ?, "
				   + "days_of_operation = ?, "
				   + "business_hours = ?, "
				   + "history_desc = ?, "
				   + "atmosphere = ?, "
				   + "is_family_friendly = ?, "
				   + "is_patio = ?, "
				   + "is_food = ?, "
				   + "is_active = ?, "
				   + "user_id = ? "
				   + "WHERE brewery_id = ?;";
		jdbcTemplate.update(sql,
							breweryToUpdate.getName(),
							breweryToUpdate.getAddress(),
							breweryToUpdate.getCity(),
							breweryToUpdate.getZip(),
							breweryToUpdate.getPhoneNumber(),
							breweryToUpdate.getDaysOpen().toString(),
							breweryToUpdate.getHours().toString(),
							breweryToUpdate.getHistory(),
							breweryToUpdate.getAtmosphere(),
							breweryToUpdate.isFamilyFriendly(),
							breweryToUpdate.hasPatio(),
							breweryToUpdate.hasFood(),
							breweryToUpdate.isActive(),
							breweryToUpdate.getBrewer().getId(),
							breweryToUpdate.getId());
		
		return getById(breweryToUpdate.getId());
		
	}

	private Brewery mapRowToBrewery(SqlRowSet rs)
	{
		String result;
		Brewery brewery = new Brewery();
		brewery.setId(rs.getInt("brewery_id"));
		brewery.setName(rs.getString("name"));		
		brewery.setAddress(rs.getString("address") != null ? rs.getString("address") : "");
		brewery.setCity(rs.getString("city") != null ? rs.getString("city") : "");
		// TODO: add state
		brewery.setZip(rs.getString("zipcode") != null ? rs.getString("zipcode") : "");
		brewery.setPhoneNumber(rs.getString("phone_number") != null ? rs.getString("phone_number") : "");
		brewery.setDaysOpen(rs.getString("days_of_operation") != null ? rs.getString("days_of_operation").split(",") : new String[0]);
		brewery.setHours(rs.getString("business_hours") != null ? rs.getString("business_hours").split(",") : new String[0]);
		brewery.setHistory(rs.getString("history_desc") != null ? rs.getString("history_desc") : "");
		brewery.setAtmosphere(rs.getString("atmosphere") != null ? rs.getString("atmosphere") : "");
		brewery.setFamilyFriendly(rs.getBoolean("is_family_friendly"));
		brewery.setPatio(rs.getBoolean("is_patio"));
		brewery.setFood(rs.getBoolean("is_food"));
		brewery.setActive(rs.getBoolean("is_active"));
		brewery.setBrewer(userDAO.getUserById(rs.getLong("user_id")));
		
		return brewery;
	}
	
	private int getNextId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('breweries_brewery_id_seq') AS next_id");
		if(nextIdResult.next()) {
			return nextIdResult.getInt("next_id");
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new brewery");
		}
	}


}
