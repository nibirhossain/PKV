package de.unistuttgart.iste.se.pkv.dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "TBE_CHAPTERS")
public class Topic 
{
	
		@DatabaseField(generatedId = true)
		private int id;
		@DatabaseField(columnName = "name")
		private String name;
		@DatabaseField(columnName = "TOPIC_BASED_EXAM_ID")
		private int examID;
		public Topic() 
		{
			// ORMLite needs a no-arg constructor
		}
		public int getID() 
		{
			return id;
		}
		public String getName() 
		{
			return name;
		}
		public int getExamID() 
		{
			return examID;
		}
		public void setName(String name) 
		{
			this.name = name ;
		}
		public void setExamID(int examID) 
		{
			this.examID = examID ;
		}
	}