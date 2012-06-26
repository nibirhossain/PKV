<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login | Prima KlausurVerbereitung</title>
<link type="text/css" rel="stylesheet" href="CSS/standard_xhtml_2.css"> 
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">

</head>
<body>
	<% 
		String isLogin = "";
		if(request.getParameter("isLogin") != null) 
			isLogin = request.getParameter("isLogin"); 
		
	%>
<form action="UserController" method="post">
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
				<div style="margin-left: 20px;">
					<fieldset style="width:60%; text-align:right;border: solid 1px #a7c5c9;">
						<% if(isLogin.equals("1")){ %>
							<font color="red">Please enter your valid username and password.</font>
						<%}
						else if(isLogin.equals("false")){ %>
						<font color="blue">You have logged out successfully..</font>
					<%}
						%>
						<br>
						<legend>Login</legend>
						<div class="liststyle">
							<ul>
								<li>User ID: <input type="text" name="username" />&nbsp;&nbsp;</li>
								<li>Password: <input type="password" name="password" />&nbsp;&nbsp;</li>
								<li><input name="btnUserLogin" type="submit" value="Login" />&nbsp;&nbsp;</li>
								<li>&nbsp;</li>								
							</ul>
						</div>						
					</fieldset>
				</div>
			</div>
			<!--TYPO3SEARCH_end-->
		</div>		
	</div>
	<%@ include file='utilPages/footer_uni.jsp' %>	
</div>
</form>
</body>
</html>