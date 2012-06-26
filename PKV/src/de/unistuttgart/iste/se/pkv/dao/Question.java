package de.unistuttgart.iste.se.pkv.dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "TBE_QUESTIONS")
public class Question 
{
	
		@DatabaseField(generatedId = true)
		private int id;
		@DatabaseField(columnName = "Name")
		private String name;
		@DatabaseField(columnName = "CreatedYear")
		private int createdYear;		 
		//@DatabaseField(columnName = "CreatedDate")
		//private Date createdDate;
		@DatabaseField(columnName = "TBE_CHAPTER_ID")
		private int chapterID ;
		
		public Question() 
		{
			// ORMLite needs a no-arg constructor
		}
		public int getID() 
		{
			return id;
		}
		
		public void setName(String name) 
		{
			this.name = name ;
		}
		public String getName() 
		{
			return name;
		}		
		public void setCreatedYear(int createdYear) 
		{
			this.createdYear = createdYear;
		}
		public int getCreatedYear() 
		{
			return createdYear;
		}
		/*public void setCreatedDate(Date createdDate) 
		{
			this.createdDate = createdDate ;
		}
		public Date getCreatedDate() 
		{
			return createdDate;
		}*/
		public void setChapterID(int chapterID) 
		{
			this.chapterID = chapterID;
		}
		public int getChapterID() 
		{
			return chapterID;
		}		
	}