package de.unistuttgart.iste.se.pkv.model;

import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import de.unistuttgart.iste.se.pkv.dao.*;
import de.unistuttgart.iste.se.pkv.utils.DBManager;


public class TBETopic
{
	private static Dao<Topic, Integer> topicDao;
	private static JdbcConnectionSource connection_source = null;
	
	public static List<Topic> getAllTopics() throws Exception
	{
		List<Topic> topics = new ArrayList<Topic>();
		try {
			// create our data-source for the database
			connection_source = DBManager.openJDBCConnection();
			// setup our database and DAOs
			setupDatabase(connection_source);
			topics = topicDao.queryForAll();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return topics;
	}
	
	public static List<Topic>  getTopics(int exam_id) throws Exception
	{
		List<Topic> topics = new ArrayList<Topic>();		
		try 
		{
			// create our data-source for the database
			connection_source = DBManager.openJDBCConnection();
			// setup our database and DAOs
			setupDatabase(connection_source);
			// read and write some data
			topics = readWriteData(exam_id);
			// do a bunch of bulk operations
			System.out.println("\n\nIt seems to have worked\n\n");
		} finally {
			// destroy the data source which should close underlying connections
			if (connection_source != null) {
				connection_source.close();
			}
		}
		return topics;
	}
	
	private static void setupDatabase(JdbcConnectionSource connectionSource) throws Exception 
	{
		topicDao = DaoManager.createDao(connectionSource, Topic.class);	
		// if you need to create the table
		//TableUtils.createTable(connectionSource, Chapters.class);
	}
	
	private static List<Topic> readWriteData(int exam_id) throws Exception 
	{
		// create an instance of Account
		// query for all items in the database
		
		/*Topic chapter1 = new Topic();
		chapter1.setName("Topic Three");
		chapter1.setExamID(2);
		chapterDao.create(chapter1);*/
		
		//List<Topic> chapters = chapterDao.queryForAll();
		List<Topic> topics = topicDao.queryForEq("TOPIC_BASED_EXAM_ID", exam_id);
		return topics;
	}
	
	public static int saveTopic(int exam_id, String topic_name) throws Exception
	{
		int isCreated = -1;
		try
		{
			connection_source = DBManager.openJDBCConnection();
			// setup our database and DAOs
			setupDatabase(connection_source);
			Topic topic = new Topic();
			topic.setName(topic_name);
			topic.setExamID(exam_id);
			isCreated = topicDao.create(topic);
		}		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return isCreated;		
	}
	
	public static void deleteTopicByID(int topic_id) throws Exception
	{
		try
		{
			connection_source = DBManager.openJDBCConnection();
			// setup our database and DAOs
			setupDatabase(connection_source);			
			topicDao.deleteById(topic_id);
		}		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
