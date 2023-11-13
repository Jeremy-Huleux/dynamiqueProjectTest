<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>Inscription</title>
</head>
<%@page import="java.util.Date"%>
	<body>
		<h1><%=new Date() %></h1>
	<div class="mt-5 row">
        <div class="mx-auto col-8 col-md-6 col-lg-4">
			<form class="form-example" action="MyServlet?flag=inscrit" method="post">
				<fieldset>
		        	<!-- <legend>Inscription</legend> -->
					<label for="firstName">Prénom </label>
			        <input name="firstName" id="firstName" autofocus="autofocus" type="text" size="20"/>       
			        <br /><br />
			
			        <label for="lastName">Nom </label>
			        <input name="lastName" id="lastName" type="text" size="20"/>
			        <br /><br />
			        
			        <label for="adresse">Adresse </label>
			
			        <select name="adresse" id="adresse">
			        	<option value="none"> Sélectionnez un pays</option>
			        	<option value="tn">Tunisie</option>
			        	<option value="es">Espagne</option>
			            <option value="fr">France</option>
			            <option value="it">Italie</option>
			    	</select>
			        <br /><br />
			
			        <label for="tel">Téléphone </label>
			        <input name="tel" id="tel" type="text" size="20"/>       
			        <br /><br />
			        
			        <label for="age">Âge </label>
			        <input name="age" id="age" type="number" size="20"/>     
			        <br /><br />
			
					<fieldset>
			        	<legend>Sexe</legend>
						<input name="sexe" id="sexeH" type="radio" value="H" size="20" checked/>
				        <label for="sexeH">Homme</label>
				        <input name="sexe" id="sexeF" type="radio" value="F" size="20"/> 
				        <label for="sexeF">Femme</label> 
			        </fieldset>      
			        <br /><br />
			
			        <label for="login">Pseudo </label>
			        <input name="login" id="login" type="text" size="20"/>        
			        <br /><br />
			
			        <label for="pwd">Mot de passe </label>
			        <input name="pwd" id="pwd" type="password" size="20"/>
			        <br /><br />
			
			        <label for="pwd2">Mot de passe (confirmation) </label>
			        <input name="pwd2" id="pwd2" type="password" size="20"/>
			        <br /><br />
				
			        <input type="submit" class="btn btn-primary" value="M'inscrire" /> 
			        <input type="reset" class="btn btn-danger" value="Vider le formulaire" />
			        <a href="menu.jsp"><input type="button" class="btn btn-primary" value="Annuler"></a>
			        <a href="connect.jsp"><input type="button" class="btn btn-primary" value="Se connecter"></a>
			        
			        <div class="container aqua">
			        	<p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p>
			        </div>
			        
				</fieldset>
		    </form>
		   </div>
		  </div>
	</body>
</html>