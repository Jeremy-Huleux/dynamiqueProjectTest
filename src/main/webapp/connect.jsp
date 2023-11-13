<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String login = request.getParameter("pseudo");
	String password = request.getParameter("mdp");
	if(login == null){login = "";}
	if(password == null){password = "";}
	
	if(request.getMethod().equals("POST") &&
			login.equals("admin") &&
			password.equals("admin")){
		
	
		
%>
		<h2>BIENVENUE <%= login %></h2>
<%

	} else {
%>
<form action="MyServlet?flag=connect" method="POST">
	<table>
		<tr>
			<td><label>Nom d'utilisateur : </label></td>
			<td><input type="text" id="pseudo" name="pseudo"></td>
		</tr>
		<tr>
			<td><label>MDP : </label></td>
			<td><input type="password" id="mdp" name="mdp"></td>
		</tr>
		<tr>
		</tr>
		<tr>
			<td><input type="submit" value="valider"></td>
			<td><input type="reset" value="annuler"></td>
		</tr>
	</table>
</form>
<%
	}
%>
</body>
</html>