package de.unistuttgart.iste.se.pkv.model;

import java.util.ArrayList;
import java.util.List;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import de.unistuttgart.iste.se.pkv.dao.Exam;
import de.unistuttgart.iste.se.pkv.dao.User;
import de.unistuttgart.iste.se.pkv.utils.DBManager;

public class TopicBasedExam 
{
	private static Dao<Exam, Integer> examDao;
	private static JdbcConnectionSource connection_source = null;
	
	public static Exam getExamByID(int exam_id) throws Exception 
	{		
		Exam exam = new Exam();		
		try 
		{
			// create our data-source for the database
			connection_source = DBManager.openJDBCConnection();
			// setup our database and DAOs
			setupDatabase(connection_source);
			// read and write some data
			exam = examDao.queryForId(exam_id);
			// do a bunch of bulk operations			
		} 
		catch(Exception e)
		{
			System.out.println("Exception in Exam Model : " + e.getMessage());
		}
		finally 
		{
			// destroy the data source which should close underlying connections
			if (connection_source != null) 
			{
				connection_source.close();
			}
		}
		return exam;
	}
	
	public static List<Exam> getExams() throws Exception 
	{		
		List<Exam> exams = new ArrayList<Exam>();		
		try 
		{
			// create our data-source for the databasehj
			connection_source = DBManager.openJDBCConnection();
			// setup our database and DAOs
			setupDatabase(connection_source);
			// read and write some data
			exams = readWriteData();
			// do a bunch of bulk operations
			return exams;
		} finally {
			// destroy the data source which should close underlying connections
			if (connection_source != null) {
				connection_source.close();
			}
		}
	}
	
	private static void setupDatabase(JdbcConnectionSource connectionSource) throws Exception 
	{
		examDao = DaoManager.createDao(connectionSource, Exam.class);	
		// if you need to create the table
		//TableUtils.createTable(connectionSource, Exams.class);
	}
	
	private static List<Exam> readWriteData() throws Exception 
	{
		// create an instance of Account
		// query for all items in the database
		
		//Exam exam1 = new Exam();
		//exam1.setName("Computer Architecture");
		//examDao.create(exam1);
		
		List<Exam> exams = examDao.queryForAll();
		return exams;
	}
	public static int saveExam(String exam_name) throws Exception
	{
		int isCreated = -1;
		try
		{
			connection_source = DBManager.openJDBCConnection();
			// setup our database and DAOs
			setupDatabase(connection_source);
			Exam exam = new Exam();
			exam.setName(exam_name);
			isCreated = examDao.create(exam);
		}		
		catch(Exception e)
		{
			System.out.println("Exception in Exam Model : " + e.getMessage());
		}
		return isCreated;		
	}
	
	public static int updateExamByID(Exam exam) throws Exception
	{
		
		int isUpdated = -1;
		try
		{
			connection_source = DBManager.openJDBCConnection();
			// setup our database and DAOs
			setupDatabase(connection_source);			
			isUpdated = examDao.update(exam);
		}		
		catch(Exception e)
		{
			System.out.println("Exception in Exam Model : " + e.getMessage());
		}
		return isUpdated;
	}
	
	public static int deleteExamByID(int exam_id) throws Exception
	{
		int isDeleted = -1;
		try
		{
			connection_source = DBManager.openJDBCConnection();
			// setup our database and DAOs
			setupDatabase(connection_source);			
			isDeleted = examDao.deleteById(exam_id);
		}		
		catch(Exception e)
		{
			System.out.println("Exception in Exam Model : " + e.getMessage());
		}
		return isDeleted;		
	}
}
