<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des clients</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value ="/inc/style.css"/>" />
</head>
<c:import url="/inc/menu.jsp"></c:import>
<body>
	<c:choose>

		<c:when test="${!empty sessionScope.listeClients }">
			<table>
				<thead>
					<tr>
						<th>Nom</th>
						<th>Prenom</th>
						<th>Adresse</th>
						<th>Téléphone</th>
						<th>Email</th>
						<th class="action">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sessionScope.listeClients}" var="element" varStatus="status">
						<c:set var="couleur">impair</c:set>
						<c:if test="${status.count mod 2 == 0}">
							<c:set var="couleur" value="pair"/>
						</c:if>	
						<tr class="${couleur}">
							<td><c:out value="${element.value.nom}"></c:out></td>
							<td><c:out value="${element.value.prenom}"></c:out></td>
							<td><c:out value="${element.value.adresse}"></c:out></td>
							<td><c:out value="${element.value.telephone}"></c:out></td>
							<td><c:out value="${element.value.email}"></c:out></td>
							<td class="supprimer"> <a href='<c:url value="/suppressionClient"><c:param name="nomClient" value="${element.value.nom}"/> </c:url>'>
							 <img alt="supprimer" src='<c:url value ="/inc/supprimer.png"></c:url>'></a> </td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<p class="erreur">Aucun client n'a été inscrit.</p>
		</c:otherwise>

	</c:choose>

</body>
</html>