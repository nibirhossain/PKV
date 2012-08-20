package de.unistuttgart.iste.se.pkv.dao;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "TOPIC_BASED_EXAMS")
public class Exam 
{
	
		@DatabaseField(generatedId = true)
		private int id;
		@DatabaseField(columnName = "name")
		private String name;
		@DatabaseField(columnName = "meta_info")
		private String exam_meta_info;
		@DatabaseField(columnName = "year")
		private int exam_year;		
		@DatabaseField(columnName = "month")
		private int exam_month;
		@DatabaseField(columnName = "created_date")
		private Date created_date;			
		
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
		public String getExamMetaInfo()
		{
			return exam_meta_info;
		}
		public void setExamMetaInfo(String exam_meta_info)
		{
			this.exam_meta_info = exam_meta_info;
		}		
		public int getExam_year() {
			return exam_year;
		}
		public void setExam_year(int exam_year) {
			this.exam_year = exam_year;
		}
		public int getExam_month() {
			return exam_month;
		}
		public void setExam_month(int exam_month) {
			this.exam_month = exam_month;
		}
		public Date getCreated_date() {
			return created_date;
		}
		public void setCreated_date(Date created_date) {
			this.created_date = created_date;
		}
	
	}