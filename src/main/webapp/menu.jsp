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
	<hr>
		<a href="accueil.jsp">
			<button class="btn btn-primary">Accueil</button>
		</a>
		<% 
		
		if(session == null){ %>
	<hr>
		<a href="connect.jsp">
			<button class="btn btn-primary">Connexion</button>
		</a>
		<% }else{ %>
		<hr>
		<a href="connexionOK.jsp">
			<button class="btn btn-primary">Retour connexion</button>
		</a>
		<% } %>
	<hr>
		<a href="inscription.jsp">
			<button class="btn btn-primary">Inscription</button>
		</a>
	<hr>
</body>
</html>