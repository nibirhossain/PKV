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
<title>Logout</title>
</head>
<body>
<%
	request.getSession(true).getAttribute("CurrentUser");
	request.getSession(false).invalidate();
	//User user = (User)(request.getSession(true).getAttribute("CurrentUser"));	
	if(request.getSession(true).getAttribute("CurrentUser") == null)
	{
		response.sendRedirect("login.jsp?isLogin=false");
	}
%>
</body>
</html>