<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="de.unistuttgart.iste.se.pkv.model.*"%>
<%@page import="de.unistuttgart.iste.se.pkv.dao.*"%>
<%@page import="de.unistuttgart.iste.se.pkv.utils.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<div id="header">
		<%
				User login_user = (User)session.getAttribute("CurrentUser");
			%>
			<% if(login_user != null) {%>
				Welcome  
				<b><%= login_user.getLastName() + ", " + login_user.getFirstName() %></b> &nbsp;&nbsp;
			<%} 
		%>
	</div>
	<div id="mainnav">
	<%
		String url = (String)request.getRequestURL().toString();			
		if(url.indexOf(Consts.SUB_URL) > 0)
		{
			if(request.getSession(true).getAttribute("CurrentUser") != null)
			{%>
				<a href="../<%=Consts.URL_HOME %>">Exam</a> | 
				<a href="../<%=Consts.EXAM_CONTROLLER %>">Manage Exams</a> |
				<a href="../<%=Consts.TOPIC_CONTROLLER %>">Manage Topics</a> |
				<a href="../<%=Consts.QUESTION_CONTROLLER %>">Manage Questions</a> |
				<a href="../<%=Consts.USER_CONTROLLER %>">Manage Admin</a> | 
				<a href="../<%=Consts.URL_LOGOUT %>">Logout</a>&nbsp;&nbsp;&nbsp;&nbsp;			
			<%
			}
			else
			{%>
				<a href="../<%=Consts.URL_LOGIN%>">Login</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<%}
		}
		else
		{
			if(request.getSession(true).getAttribute("CurrentUser") != null)
			{%>
				<a href="<%=Consts.URL_HOME %>">Exam</a> | 
				<a href="<%=Consts.EXAM_CONTROLLER %>">Manage Exams</a> |
				<a href="<%=Consts.TOPIC_CONTROLLER %>">Manage Topics</a> |
				<a href="<%=Consts.QUESTION_CONTROLLER %>">Manage Questions</a> |
				<a href="<%=Consts.USER_CONTROLLER %>">Manage Admin</a> | 
				<a href="<%=Consts.URL_LOGOUT %>">Logout</a>&nbsp;&nbsp;&nbsp;&nbsp;			
			<%
			}
			else
			{%>
				<a href="<%=Consts.URL_LOGIN%>">Login</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<%}	
		}		
	%>		
	</div>
