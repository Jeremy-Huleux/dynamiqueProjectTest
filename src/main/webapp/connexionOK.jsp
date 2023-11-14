<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javaProjectCnx.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>
	<h1>Bienvenue ${user.fName} ${user.lName} sous le pseudo : ${compte.login}</h1>
	<h1>Vous êtes : 
		<% 
			Compte c = new Compte();
			c = (Compte)session.getAttribute("compte");
			String type = c.getType();
			//out.print(type);
			if(type.equals("s")){
				%>simple utilisateur<% 
			}else if(type.equals("a")){
				%>Administrateur<%
			}else{
				%> ERREUR BASE DE DONNEE NON/MAL RENSEIGNE <%
			}
		%>
	</h1>
<hr>
	<a href="accueil.jsp">
		<button class="btn btn-primary">Accueil</button>
	</a>
<hr>
	<a href="listecat.jsp">
		<button class="btn btn-primary">Liste des Catégories</button>
	</a>
<hr>
	<a href="listeArticle.jsp">
		<button class="btn btn-primary">Liste des Articles</button>
	</a>
<hr>
<% if(type.equals("a")){ %>
	<a href="admin.jsp">
		<button class="btn btn-primary">Panneau Admin</button>
	</a>
<hr>
<% } //${variable ? valeur_si_vrai : valeur_si_faux} variable ternaire%>
	<a href="disconnect.jsp">
		<button class="btn btn-primary">Deconnexion</button>
	</a>
<hr>
</body>
</html>