<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="javaProjectCnx.*" %>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Liste de cat</title>
</head>
<body>
<table>
	<thead>
		<tr>
			<th >Numéros de catégorie</th>
			<th >Designation</th>
		</tr>
	</thead>
	<tbody>
		<%
		CreerConnexion cc = new CreerConnexion();
		cc.etablirConnexion();
		String td = cc.listCat(); 
		out.print(td);
		%>
	</tbody>
</table>
</body>
</html>