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
<title>Create Subject |  Prima KlausurVerbereitung</title>
<link type="text/css" rel="stylesheet" href="../CSS/standard_xhtml_2.css"> 
<link rel="shortcut icon" href="../images/favicon.ico" type="image/x-icon">
</head>
<body>
<%
	if(request.getSession(true).getAttribute("CurrentUser") == null)
	{
		response.sendRedirect("../login.jsp?isLogin=false");
	}
%>
<% 
	String isCreated = request.getParameter("isCreated");		
%>
<form action="../TopicController" method="post">
<%@ include file='../utilPages/header_uni1.jsp' %>
<div id="container">
	<div id="main">
		<div id="navirechts">
			<div id="rightimage"><img alt="" title="" src="../images/091001_Home.png" width="186"></div>
			<div id="webcode"></div>			
			<div id="rightall">
				<div class="news-latest-container">
					<strong>Right Side Contents</strong>
				</div>
			</div>
			<div id="rightcontent"></div>
		</div>
		<div id="content">
			<!--TYPO3SEARCH_begin-->
			<div id="text">
					<%
			if(isCreated != null)
			{
			if(isCreated.equals("true"))
			{ %>
				<font color="blue">Topic is created successfully.</font>
			<%}
			else if(isCreated.equals("false"))
			{ %>
				<font color="red">Topic is not created.</font>
			<%}
			else if(isCreated.equals("1"))
			{ %>
				<font color="red">Please enter the Topic name.</font>
			<%}
			}
			%>
			<br>
			<div>
				<ul>
					<li>Select Exam : 
						<select name="ddlExam">  
				       		<option value="0">---- Select Exam ----</option>
							<%											
								List<Exam> exams = new ArrayList<Exam>();
								exams = TopicBasedExam.getExams();
								if(exams != null)
								{
									for(Exam exam : exams)
									{
										%>
											<option value="<%=exam.getID()%>"><%=exam.getName() %></option>
										<%			
									}						
								}
							%>
						</select>
					</li>							
					<li>Please enter the topic name : <input name="txtTopic" type="text" size="30"></input></li>		    
		    		<li><input name="createTopicButton" value="Create" type="submit"/></li>
		    	</ul>
		    </div>
			</div>
			<!--TYPO3SEARCH_end-->
		</div>		
	</div>
	<%@ include file='../utilPages/footer_uni.jsp' %>	
</div> 
</form>
</body>
</html>