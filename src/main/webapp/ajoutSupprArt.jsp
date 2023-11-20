<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ page import="javaProjectCnx.*" %>
<!DOCTYPE html>
<% getElementById("myform").reset(); %>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Ajout et suppression d'Articleusse</title>
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
		/* CreerConnexion cc = new CreerConnexion();
		cc.etablirConnexion();
		String td = cc.listArt(); 
		out.print(td); */
		%>
	</tbody>
</table>
</br></br></br></br></br>
<form class="form-exemple" action="MyServlet?flag=ajoutArt" method="post">
<table>
<tr>
	<td><label for="nomArt">Nom de l'article </label></td>
    <td><input name="nomArt" id="nomArt" autofocus="autofocus" type="text" size="20"/></td>       
</tr>
<tr>
    <td><label for="prixU">Prix unitaire </label></td>
    <td><input name="prixU" id="prixU" autofocus="autofocus" type="text" size="20"/></td>       
</tr>
<tr>
    <td><label for="quantite">Quantité</label></td>
    <td><input name="quantite" id="quantite" autofocus="autofocus" type="text" size="20"/></td>       
</tr>
<tr>
	<td><label for="cat">Categorie :</label>
	<td><input name="cat" id="cat" autofocus="autofocus" type="text" size="20"/></td>    
    <!--<td><select name="cat" id="cat">
		<%
	/* 	String choix = cc.listCatChoix(); 
		out.print(choix); */
		%>
   	</select></td>-->
</tr>
<tr>
    <td><input type="submit" class="btn btn-primary" value="Ajouter" /></td>
    <td><input type="submit" class="btn btn-danger" value="Supprimer" /></td>
</tr>
<tr>
    <td colspan="2" ALIGN="CENTER"><a href="menu.jsp"><input type="button" class="btn btn-primary" value="Retour au menu"></a></td>
</tr>
</table>    
</form>
</body>
</html>