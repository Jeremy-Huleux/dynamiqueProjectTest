<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="javaProjectCnx.*" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Ajout de categorie</title>
</head>
<body>
<form action="form-exemple" action="MyServlet?flag=ajoutCat" method="post">
<fieldset>
	<label for="nomCat">Nom de la catégorie </label>
    <input name="nomCat" id="nomCat" autofocus="autofocus" type="text" size="20"/>       
    <br /><br />
    <input type="submit" class="btn btn-primary" value="Ajouter" /> 
    <input type="reset" class="btn btn-danger" value="Vider" />
    </br></br>
    <a href="menu.jsp"><input type="button" class="btn btn-primary" value="Retour au menu"></a>    
</fieldset>
</form>
</body>
</html>