package de.unistuttgart.iste.se.pkv.utils;

import com.j256.ormlite.jdbc.JdbcConnectionSource;

public class DBManager 
{
	private static JdbcConnectionSource connection_source = null;
	
	private DBManager() throws Exception
	{
		try 
		{
			connection_source = 
					new JdbcConnectionSource
					(
						Consts.DATABASE_URL, 
						Consts.DATABASE_USERNAME, 
						Consts.DATABASE_PASSWORD
					);			
		} 
		catch (Exception e) 
		{
			connection_source = null;
			System.out.println("Exception: " + e.getMessage());
		}		
	}
	public static JdbcConnectionSource openJDBCConnection() throws Exception
	{
			new DBManager();
			return connection_source;
	}
}
