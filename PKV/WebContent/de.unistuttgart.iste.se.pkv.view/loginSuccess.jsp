<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="de.unistuttgart.iste.se.pkv.dao.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome | Prima KlausurVerbereitung</title>
<link type="text/css" rel="stylesheet" href="../CSS/standard_xhtml_2.css"> 
<link rel="shortcut icon" href="../images/favicon.ico" type="image/x-icon">
</head>
<body>
<%
	if(request.getSession(true).getAttribute("CurrentUser") == null)
	{
		response.sendRedirect("login.jsp?isLogin=false");
	}
%>
<form action="../UserController" method="post">
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
					<br/><br/>
				<h3>Welcome to Prima Klausur Vorbereitung!!!</h3>		
			</div>
			<!--TYPO3SEARCH_end-->
		</div>
		
	</div>
	<%@ include file='../utilPages/footer_uni.jsp' %>	
</div> 
</form>
</body>
</html>
