<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="de.unistuttgart.iste.se.pkv.dao.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Topic-based Examination | Prima KlausurVerbereitung</title>
<link type="text/css" rel="stylesheet" href="CSS/standard_xhtml_2.css"> 
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
</head>
<body>
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
					<!-- <h4>Institut für Softwaretechnologie</h4> -->
			<%
				Exam exam = (Exam)request.getAttribute("exam");
			%>
			<h4>EXAMINATION IN <%=exam.getName().toUpperCase() %></h4>
			
			<table width="100%" cellpadding="0" cellspacing="0" border="0">						
			<%			
				int count = 1;
				List<Question> questions = (List) request.getAttribute("Questions");
				if(questions != null)
				{
					for(Question question : questions)
					{				
					%>						
						<tr>
							<td style="border-top: solid 1px #000; vertical-align:top;" align="left"><b>Problem. <%=count %> </b>&nbsp;(10 Points) &nbsp;<%=question.getName() %></td>							
																			
						</tr>								
						<tr>
							<td>&nbsp;</td>
						</tr>
					<%
					count++;
					} 
				}
			%>
			</table>
			</div>
			<!--TYPO3SEARCH_end-->
		</div>
		
	</div>
	<%@ include file='../utilPages/footer_uni.jsp' %>	
</div> 
</body>
</html>