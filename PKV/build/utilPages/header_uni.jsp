<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="de.unistuttgart.iste.se.pkv.model.*"%>
<%@page import="de.unistuttgart.iste.se.pkv.dao.*"%>
<%@page import="de.unistuttgart.iste.se.pkv.utils.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<div id="globalnavi" style="text-align: right;">
		<ul id="globalnavilist">
			<li class="home_icon"><a href="http://www.uni-stuttgart.de/" title="uni" target="_top">uni</a>
			</li><li class="home_icon"><a href="http://www.informatik.uni-stuttgart.de/home.html" title="home" target="_top">home</a></li>
			<li class="suche_icon"><a href="http://www.informatik.uni-stuttgart.de/suche.html" title="Suche">suche</a></li>
			<li class="sitemap_icon"><a href="http://www.informatik.uni-stuttgart.de/sitemap.html" title="Sitemap">sitemap</a></li>
			<li class="kontakt_icon"><a href="http://www.informatik.uni-stuttgart.de/kontakt.html" title="Kontakt">kontakt</a></li>
			<li class="drucker_icon"><a title="Druckansicht" onclick="window.print();">print</a></li>
		</ul>
	</div>
	<div id="printlogo">
		<!-- inkludieren von "/info/elements/print_header_2.0.jsp" -->
		<img src="images/printlogo.gif" alt="Printlogo">	
	</div>
	
	<div id="header">
	<div id="portalueberschrift">Die Informatik in Stuttgart</div>
	<a href="http://www.uni-stuttgart.de/" title="zur Homepage der Universität Stuttgart">
    	<span id="link_uni"></span>
    </a>
    <a href="http://www.informatik.uni-stuttgart.de/index.php" title="zur Startseite">
    	<span id="link_unit"></span>
    </a>	
</div>
<div id="leftnavibox">Left navi box
	<%
		User login_user = (User)session.getAttribute("CurrentUser");
		if(login_user != null) 
		{
	%>
		Welcome  <b><%= login_user.getLastName() + ", " + login_user.getFirstName() %></b> &nbsp;&nbsp;
	<%	
		} 
	%>
</div>
<div id="startseite"><a title="Zur Webseite der Uni Stuttgart" href="http://www.uni-stuttgart.de/"><img src="images/pixeltrans.gif" style="top: 0pt; left: 0pt; border: medium none;" alt="Zur Webseite der Uni Stuttgart" title="Zur Webseite der Uni Stuttgart" width="26" height="20"></a></div>

<div id="hauptnavi">
	<ul class="menu">
		<li><a href="http://www.informatik.uni-stuttgart.de/home.html">Home</a></li>
		<%
		String url = (String)request.getRequestURL().toString();			
			if(request.getSession(true).getAttribute("CurrentUser") != null)
			{%>
		
		<li><a href="#">Exams</a>
			<ul>
				<li><a href="<%=Consts.URL_HOME %>">Topic Based Exam</a></li>
			</ul>
		</li>
		<li><a href="#">Manage Exams</a>
			<ul>
				<li><a href="<%=Consts.EXAM_CONTROLLER %>">View Exams</a></li>
			</ul>
		</li>
				<li><a href="#">Manage Topics</a>
			<ul>
				<li><a href="<%=Consts.TOPIC_CONTROLLER %>">View Topics</a></li>
			</ul>
		</li>
				<li><a href="#">Manage Questions</a>
			<ul>
				<li><a href="<%=Consts.QUESTION_CONTROLLER %>">View Questions</a></li>
			</ul>
		</li>
				<li><a href="#">Manage Admin</a>
			<ul>
				<li><a href="<%=Consts.USER_CONTROLLER %>">View Admins</a></li>
			</ul>
		</li>
		<li><a href="<%=Consts.URL_LOGOUT %>">Logout</a></li>
		<%
}
else
{%>
		<li><a href="<%=Consts.URL_LOGIN%>">Login</a></li>		
<%
}
%>				
	</ul>
</div>			
