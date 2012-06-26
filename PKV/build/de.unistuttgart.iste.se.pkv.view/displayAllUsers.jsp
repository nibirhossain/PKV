<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="de.unistuttgart.iste.se.pkv.dao.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display Users |  Prima KlausurVerbereitung</title>
<link type="text/css" rel="stylesheet" href="CSS/standard_xhtml_2.css"> 
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
</head>
<body>
<%
	if(request.getSession(true).getAttribute("CurrentUser") == null)
	{
		response.sendRedirect("login.jsp?isLogin=false");
	}
%>
<form action="UserController" method="post">
<%@ include file='../utilPages/header_uni.jsp' %>
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
					<div>
				<table width="100%" cellpadding="0" cellspacing="0" border="1">
					<tr>
						<td width="5%"><b>No.</b></td>
						<td width="40%"><b>Last Name</b></td>
						<td width="40%"><b>First Name</b></td>
						<td width="15%"><b>Edit | Delete</b></td>											
					</tr>
					<%
					int count = 1;
					List<User> users = (List) request.getAttribute("allUsers");
					if(users != null)
					{
						for(User user : users)
						{				
						%>						
							<tr>
								<td><%=count %></td>								
								<td align="left"><%=user.getFirstName() %></td>
								<td align="left"><%=user.getLastName() %></td>
								<td>
									<a href="de.unistuttgart.iste.se.pkv.view/editUser.jsp?userID= <%=user.getID() %>&action=edit">Edit</a> |
									<a href="../register.jsp?userID= <%=user.getID() %>&action=delete">Delete</a>
								</td>														
							</tr>			
						<%
						count++;
						} 
					} 
					%>
					
				</table>
				<br><br>
				<input name="btnAddUser" value="Create New Admin" type="submit" />
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