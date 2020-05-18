<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>creationClient</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css"/>" />
</head>

<c:import url="/inc/menu.jsp"></c:import>

<body>
	<p class="info">
		<c:out value="${formulaire.resultat}"></c:out>
	</p>
	<p>
		Nom :
		<c:out value="${client.nom}"></c:out>
	</p>
	<p>
		Prénom :
		<c:out value="${client.prenom}"></c:out>
	</p>
	<p>
		Adresse :
		<c:out value="${client.adresse}"></c:out>
	</p>
	<p>
		Numéro de téléphone :
		<c:out value="${client.telephone}"></c:out>
	</p>
	<p>
		Email :
		<c:out value="${client.email}"></c:out>
	</p>
</body>
</html>