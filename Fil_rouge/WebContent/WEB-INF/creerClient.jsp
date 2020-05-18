<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Création d'un client</title>
<link type="text/css" rel="stylesheet" href="<c:url value ="/inc/style.css"/>"/>
</head>

<c:import url="/inc/menu.jsp"></c:import>

<body>
	<div>
		<form method="post" action=<c:url value="/creationClient"/>>
			<c:import url="/inc/inc_client_form.jsp"></c:import>
			<input type="submit" value="Valider" /> <input type="reset"
				value="Remettre à zéro" />
		</form>
		<p class="info">
			${formulaire.resultat}
		</p>
	</div>
</body>
</html>