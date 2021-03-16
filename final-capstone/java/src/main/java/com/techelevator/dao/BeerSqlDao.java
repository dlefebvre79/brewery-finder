package com.techelevator.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Beer;
import com.techelevator.model.User;
import com.techelevator.model.exceptions.BeerNotFoundException;

@Service
public class BeerSqlDao implements BeerDAO
{

    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserDAO userDAO;

	public BeerSqlDao(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	
	@Override
	public List<Beer> findAll()
	{
		List<Beer> beer = new ArrayList<>();
		String sql = "SELECT * FROM beer";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next())
		{
			Beer beers = mapRowToBeer(results);
			beer.add(beers);
		}
		
		return beer;
	}

	@Override
	public Beer getById(int id)
	{
		String sql = "SELECT * FROM beer WHERE beer_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		if(results.next())
		{
			return mapRowToBeer(results);
		}
		else
		{
			throw new RuntimeException("beerId " + id + " was not found.");
		}
	}

	@Override
	public List<Beer> getByName(String name)
	{
		List<Beer> beers = new ArrayList<>();
		for (Beer beer : this.findAll())
		{
			if (beer.getName().toLowerCase().contains(name.toLowerCase()))
			{
				beers.add(beer);
			}
		}
		if(beers.size() == 0)
		{
			throw new BeerNotFoundException();	
		}
		return beers;
		
		
	}
	
	
	@Override
	public int create(Beer beer)
	{
		int beerId = getNextId();
		String sql = "INSERT INTO beer "
				   + "(beer_id, name, type, info, abv, ibu, brewery_id) "
				   + "values(?, ?, ?, ?, ?, ?, ?);";
		
		jdbcTemplate.update(sql,
							beer.getId(),
							beer.getName(),
							beer.getType(),
							beer.getInfo(),
							beer.getAbv(),
							beer.getIbu(),
							beer.getBreweryId());
		return beerId;
	}
	
	@Override
	public boolean deleteBeerById(int id)
	{
		
		String sql = "UPDATE beer"
					+ " SET is_active = false"
					+ " WHERE beer_id = ?;";
		
		int success = jdbcTemplate.update(sql, id);
		
		return success > 0;
	}
	
	@Override
	public Beer update(Beer beerToUpdate)
	{
		
		String sql = "UPDATE beer "
				   + "SET name = ?, "
				   + "abv = ?, "
				   + "ibu = ?, "
				   + "type = ?, "
				   + "info = ?, "
				   + "brewery_id = ?, "
				   + "is_active = ?, "
				   + "WHERE beer_id = ?;";		
		jdbcTemplate.update(sql,
							beerToUpdate.getName(),
							beerToUpdate.getAbv(),
							beerToUpdate.getIbu(),
							beerToUpdate.getType(),
							beerToUpdate.getInfo(),
							beerToUpdate.getBreweryId(),
							beerToUpdate.isActive(),
							beerToUpdate.getId());
		
		return getById(beerToUpdate.getId());
	}
	
	@Override
	public List<Beer> getByBreweryId(int id)
	{
		List<Beer> beers = new ArrayList<>();
		String sql = "SELECT * FROM beer WHERE brewery_id = ? AND is_active = true";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		while(results.next())
		{
			Beer beer = mapRowToBeer(results);
			beers.add(beer);
		}
		
		return beers;
	}

	
	private Beer mapRowToBeer(SqlRowSet rows)
	{
		String result;
		Beer beer = new Beer();
		beer.setId(rows.getInt("beer_id"));
		beer.setName(rows.getString("name"));
		beer.setAbv(rows.getBigDecimal("abv"));
		beer.setIbu(rows.getInt("ibu"));
		beer.setType(rows.getString("type"));
		beer.setInfo(rows.getString("info"));
		beer.setBreweryId(rows.getInt("brewery_id"));
		beer.setActive(rows.getBoolean("is_active"));
		
		return beer;
	}
	
	private int getNextId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('beer_beer_id_seq') AS next_id");
		if(nextIdResult.next()) {
			return nextIdResult.getInt("next_id");
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new beer");
		}
	}
	
	
	
	
	
}
