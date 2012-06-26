<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="de.unistuttgart.iste.se.pkv.model.*"%>
<%@page import="de.unistuttgart.iste.se.pkv.dao.*"%>
<%@page import="de.unistuttgart.iste.se.pkv.utils.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Questions | Prima KlausurVerbereitung</title>
<script type="text/javascript" src="JS/dropdownHelper.js"></script>
<link type="text/css" rel="stylesheet" href="CSS/standard_xhtml_2.css"> 
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon"> 
</head>
<body>
<% 
	String isShow = request.getParameter("isShow");		
%>
<form action="QuestionController" method="post">
<%@ include file='utilPages/header_uni.jsp' %>

<div id="container">
	<div id="main">
		<div id="navirechts">
			<div id="rightimage"><img alt="" title="" src="images/091001_Home.png" width="186"></div>
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
				<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td colspan="2" align="right">
						<%
							if(isShow != null)
							{
							if(isShow.equals("true"))
							{ %>
								<font color="blue">Question is created successfully.</font>
							<%}
							else if(isShow.equals("false"))
							{ %>
								<font color="red">Question is not created.</font>
							<%}
							else if(isShow.equals("1"))
							{ %>
								<font color="red">Please select the subject and chapter.</font>
							<%}
							}
						%>
					</td>
					<td width="100">&nbsp;</td>
				</tr>
				<tr>
					<td align="right">Select Exam : </td>
					<td width="310" align="right">
						<select style="background-color: #ccc;width:300px;" name='ddlExam' onchange="showTopics(this.value)">  
				       		<option value="-1">---- Select Exam ----</option>
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
					</td>
					<td width="100" align="right">&nbsp;</td>
				</tr>				
				<tr>
					<td align="right">Select Topic :  </td>
					<td width="310" align="right">
						<div style="background-color: #ccc;width:300px;position: inline;" id='ddlTopic'>  		    	 
					    	<select name='ddlTopic' disabled="true" style="width:300px">  
					        	<option value='-1'>---- Select Topic ----</option>  
					      	</select>  
					    </div>
					</td>
					<td width="100" align="right">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input name="btnStartExam" value="<%=Consts.EXAM_GENERATE %>" type="submit"/>
					</td>
					<td width="100" align="right">&nbsp;</td>
				</tr>
			</table>						
			</div>
			<!--TYPO3SEARCH_end-->
		</div>		
	</div>
	<%@ include file='utilPages/footer_uni.jsp' %>	
</div>
</form>  
</body>
</html>