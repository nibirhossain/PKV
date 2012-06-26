<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@page import="de.unistuttgart.iste.se.pkv.model.*"%>
<%@page import="de.unistuttgart.iste.se.pkv.dao.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String exam_id = request.getParameter("exam");		
	String buffer="Select Topic : <select name='ddlTopic'><option value='-1'>---- Select Topic ----</option>";
	List<Topic> topics = new ArrayList<Topic>();
	topics = TBETopic.getTopics(Integer.parseInt(exam_id));
	if(topics != null)
	{
		for(Topic topic : topics)
		{
			buffer=buffer+"<option value='" + topic.getID() + "'>" + topic.getName() + "</option>";	
		}						
	}
	buffer=buffer+"</select>";  
 	response.getWriter().println(buffer);
%> 
</body>
</html>
