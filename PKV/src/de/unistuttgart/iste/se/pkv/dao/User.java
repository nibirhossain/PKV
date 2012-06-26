package de.unistuttgart.iste.se.pkv.dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "USERS")
public class User 
{
	
		@DatabaseField(generatedId = true)
		private int id;
		@DatabaseField(columnName = "login")
		private String login;
		@DatabaseField(columnName = "password")
		private String password;		 
		@DatabaseField(columnName = "LastName")
		private String lastName;
		@DatabaseField(columnName = "FirstName")
		private String firstName ;
		
		public User() 
		{
			// ORMLite needs a no-arg constructor
		}
		public int getID() 
		{
			return id;
		}
		
		public void setUsername(String login) 
		{
			this.login = login ;
		}
		public String getUsername() 
		{
			return login;
		}		
		public void setPassword(String password) 
		{
			this.password = password;
		}
		public String getPassword() 
		{
			return password;
		}
		public void setLastName(String lastName) 
		{
			this.lastName = lastName;
		}
		public String getLastName() 
		{
			return lastName;
		}
		public void setFirstName(String firstName) 
		{
			this.firstName = firstName;
		}
		public String getFirstName() 
		{
			return firstName;
		}		
	}