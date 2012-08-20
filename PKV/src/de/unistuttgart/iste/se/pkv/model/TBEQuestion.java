package de.unistuttgart.iste.se.pkv.model;

import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import de.unistuttgart.iste.se.pkv.dao.*;
import de.unistuttgart.iste.se.pkv.utils.DBManager;


public class TBEQuestion
{
	private static Dao<Question, Integer> questionDao;
	private static JdbcConnectionSource connection_source = null;
		
	public static List<Question> getAllQuestions() throws Exception
	{
		List<Question> questions = new ArrayList<Question>();
		JdbcConnectionSource connection_source = null;
		try 
		{
			// create our data-source for the database
			connection_source = DBManager.openJDBCConnection();
			// setup our database and DAOs
			setupDatabase(connection_source);
			// read and write some data
			questions = questionDao.queryForAll();
		} finally {
			// destroy the data source which should close underlying connections
			if (connection_source != null) {
				connection_source.close();
			}
		}
		return questions;
	}

	public static List<Question> getQuestions(int subject_id, int chapter_id) throws Exception
	{
		List<Question> questions = new ArrayList<Question>();
		try 
		{
			// create our data-source for the database
			connection_source = DBManager.openJDBCConnection();
			// setup our database and DAOs
			setupDatabase(connection_source);
			// read and write some data
			//questions = readWriteData();
			questions = readWriteData(subject_id, chapter_id);
			// do a bunch of bulk operations
			System.out.println("\n\nIt seems to have worked\n\n");
		} finally {
			// destroy the data source which should close underlying connections
			if (connection_source != null) {
				connection_source.close();
			}
		}
		return questions;
	}
	private static void setupDatabase(JdbcConnectionSource connectionSource) throws Exception 
	{
		questionDao = DaoManager.createDao(connectionSource, Question.class);	
		// if you need to create the table
		//TableUtils.createTable(connectionSource, Questions.class);
	}
	
	/*
	private List<Question> readWriteData() throws Exception 
	{
		// create an instance of Account
		// query for all items in the database
		
		/*Questions question1 = new Questions();
		question1.setName("What is Design Pattern?");
		question1.setCreatedYear(2012);
		question1.setCreatedDate(new Date("1/1/2012"));
		question1.setChapterID(4);
		questionDao.create(question1);*/
		
/*		List<Question> questions = questionDao.queryForAll();
		for (Question question : questions)
		{
			System.out.print("Question ID: " + question.getID() + " Question Name: " + question.getName() + " Topic ID: " + question.getCreatedYear() + " Topic ID: " + question.getChapterID() + "\n");
		}
		return questions;
	}
*/
private static List<Question> readWriteData(int subject_id, int chapter_id) throws Exception 
{
	// create an instance of Account
	// query for all items in the database
	
	/*Questions question1 = new Questions();
	question1.setName("What is Design Pattern?");
	question1.setCreatedYear(2012);
	question1.setCreatedDate(new Date("1/1/2012"));
	question1.setChapterID(4);
	questionDao.create(question1);*/
	
	List<Question> questions = questionDao.queryForEq("TBE_TOPIC_ID", chapter_id);
	for (Question question : questions)
	{
		System.out.print("Question ID: " + question.getID() + " Question Name: " + question.getName() + " Topic ID: " + question.getCreatedYear() + " Topic ID: " + question.getChapterID() + "\n");
	}
	return questions;
}
public static int saveQuestion(String question_name, int chapter_id) throws Exception
{
	int isCreated = -1;
	try
	{
		connection_source = DBManager.openJDBCConnection();
		// setup our database and DAOs
		setupDatabase(connection_source);
		Question question = new Question();
		question.setName(question_name);
		question.setChapterID(chapter_id);
		question.setCreatedYear(2012);
		isCreated = questionDao.create(question);
	}		
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return isCreated;		
}

public static void deleteQuestionByID(int question_id) throws Exception
{
	try
	{
		connection_source = DBManager.openJDBCConnection();
		// setup our database and DAOs
		setupDatabase(connection_source);			
		questionDao.deleteById(question_id);
	}		
	catch(Exception e)
	{
		e.printStackTrace();
	}
}

}
