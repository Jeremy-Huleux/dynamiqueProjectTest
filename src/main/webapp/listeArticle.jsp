<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javaProjectCnx.*" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Liste Article</title>
</head>
<body>
	<table>
	<thead>
		<tr>
			<th >#</th>
			<th >Designation</th>
			<th >Prix unitaire</th>
			<th >Quantité</th>
			<th >Categorie</th>
		</tr>
	</thead>
	<tbody>
		<%
		CreerConnexion cc = new CreerConnexion();
		cc.etablirConnexion();
		String td = cc.listArt(); 
		out.print(td);
		%>
	</tbody>
	</table>
<hr>
	<a href="accueil.jsp">
		<button class="btn btn-primary">Accueil</button>
	</a>
</body>
</html>