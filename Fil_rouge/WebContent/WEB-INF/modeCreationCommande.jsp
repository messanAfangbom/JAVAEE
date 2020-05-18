<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mode de création Commmande</title>

<link type="text/css" rel="stylesheet"
	href=<c:url value="/inc/style.css"/> />

</head>

<c:import url="/inc/menu.jsp"></c:import>

<body>
	<p>Vous avez la possiblilité de choisir un(e) client(e) déjà
		enregistré(e) pour créer votre commande.</p>
	<form method="get" action=<c:url value="/creationCommande"/>>
		<label for="clientExistant">Choisissez le client</label> <br> 
		<select name="clientExistant" id="clientsExistant">
			<c:forEach items="${sessionScope.listeClients}" var="element">
				<option value="${element.value.nom}">
					<c:out value="${element.value.nom}"></c:out>
					<c:out value="${element.value.prenom }"></c:out>
				</option>
			</c:forEach>
			<option value="nouveauClient">Creer avec nouveau client</option>
		</select> <br>
		<p>
		<input type="submit" value="Valider">
	</p>
	</form>

	

</body>
</html>