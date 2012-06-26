package de.unistuttgart.iste.se.pkv.dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "TOPIC_BASED_SUBJECTS")
public class Exam 
{
	
		@DatabaseField(generatedId = true)
		private int id;
		@DatabaseField(columnName = "name")
		private String name;
		public Exam() 
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
		public void setName(String name) 
		{
			this.name = name ;
		}
	}