package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User
{

	private static final int LEGAL_AGE = 21;
	private Long id;
	private String username;
	@JsonIgnore
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String zip;
	private LocalDate birthDate;
	private boolean subscribed;
	@JsonIgnore
	private boolean activated;
	private Set<Authority> authorities = new HashSet<>();

	public User()
	{
	}

	public User(Long id, String username, String password, String authorities)
	{
		this.id = id;
		this.username = username;
		this.password = password;
		this.activated = true;
	}

	public User(Long id, String username, String password, String authorities,
				String firstName, String lastName, LocalDate birthDate, String email, boolean subscribed)
	{
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
		this.subscribed = subscribed;
		this.activated = true;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getZip()
	{
		return zip;
	}

	public void setZip(String zip)
	{
		this.zip = zip;
	}

	public LocalDate getBirthDate()
	{
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate)
	{
		this.birthDate = birthDate;
	}

	public boolean isSubscribed()
	{
		return subscribed;
	}

	public void setSubscribed(boolean subscribed)
	{
		this.subscribed = subscribed;
	}

	public boolean isActivated()
	{
		return activated;
	}

	public void setActivated(boolean activated)
	{
		this.activated = activated;
	}

	public Set<Authority> getAuthorities()
	{
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities)
	{
		this.authorities = authorities;
	}

	public void setAuthorities(String authorities)
	{
		String[] roles = authorities.split(",");
		for (String role : roles)
		{
			String authority = role.contains("ROLE_") ? role : "ROLE_" + role;
			this.authorities.add(new Authority(authority));
		}
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User) o;
		return id == user.id &&
				activated == user.activated &&
				subscribed == user.subscribed &&
				Objects.equals(username, user.username) &&
				Objects.equals(password, user.password) &&
				Objects.equals(firstName, user.firstName) &&
				Objects.equals(lastName, user.lastName) &&
				Objects.equals(birthDate, user.birthDate) &&
				Objects.equals(email, user.email) &&
				Objects.equals(zip, user.zip) &&
				Objects.equals(authorities, user.authorities);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, username, password, firstName, lastName, birthDate, email, zip, subscribed, activated, authorities);
	}

	@Override
	public String toString()
	{
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", firstName=" + firstName +
				", lastName=" + lastName +
				", birthDate=" + birthDate +
				", email=" + email+
				", zip=" + zip+
				", subscribed=" + subscribed +
				", activated=" + activated +
				", authorities=" + authorities +
				'}';
	}
	
}
