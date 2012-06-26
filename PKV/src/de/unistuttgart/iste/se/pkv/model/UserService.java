package de.unistuttgart.iste.se.pkv.model;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import de.unistuttgart.iste.se.pkv.dao.User;
import de.unistuttgart.iste.se.pkv.utils.DBManager;

public class UserService 
{
	private static Dao<User, Integer> userDao;
	private static JdbcConnectionSource connection_source = null;
	//private static User loginUser;
	
	public static List<User> getAllUsers() throws Exception
	{
		List<User> users = new ArrayList<User>();
		try {
			connection_source = DBManager.openJDBCConnection();
			// setup our database and DAOs
			setupDatabase(connection_source);
			users = userDao.queryForAll();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return users;
	}
	public static User authenticateUser(User user) throws Exception
	{		
		try 
		{
			String password = user.getPassword();
			// create our data-source for the database
			connection_source = DBManager.openJDBCConnection();
			// setup our database and DAOs
			setupDatabase(connection_source);
			// read and write some data
			user = readWriteData(user.getUsername());
			if(password.equals(user.getPassword()))
			{
				return user;
			}
			return user;
		} finally {
			// destroy the data source which should close underlying connections
			if (connection_source != null) {
				connection_source.close();
			}
		}
	}
	
	public static int saveUser(User user) throws Exception
	{
		int isCreated = -1;
		connection_source = DBManager.openJDBCConnection();
		// setup our database and DAOs
		setupDatabase(connection_source);
		isCreated = userDao.create(user);
		return isCreated;
	}
	
	private static void setupDatabase(JdbcConnectionSource connectionSource) throws Exception 
	{
		userDao = DaoManager.createDao(connectionSource, User.class);	
		// if you need to create the table
		//TableUtils.createTable(connectionSource, Chapters.class);
	}
	
	private static User readWriteData(String username) throws Exception 
	{
		User user = new User();
		// create an instance of Account
		// query for all items in the database			
		List<User> users = userDao.queryForEq("login", username);
		
		for(User loginUser : users)
		{
			user = loginUser;
		}
		return user;
	}	
	
}

