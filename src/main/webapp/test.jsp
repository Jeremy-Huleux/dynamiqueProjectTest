<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://hasni.fr/tld/Login" prefix="log" %>
<html>
  <head>
    <title>Exemple</title>
  </head>

  <body>
      <c:out value="Bonjour" /><br/>
      <c:forTokens items = "Zara,nuha,roshy" delims = "," var = "name">
         <c:out value = "${name}"/><p>
      </c:forTokens>
      
      <c:forEach var = "i" begin = "1" end = "5">
         Item <c:out value = "${i}"/><p>
      </c:forEach>
      <log:Login action="MyServlet?flag=connect" login="${login}" pwd="${pwd}" />
			
  </body>
</html>