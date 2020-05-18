<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste commandes</title>
<link type="text/css" rel="stylesheet" href="<c:url value ="/inc/style.css"/>"/>
</head>
<c:import url="/inc/menu.jsp"></c:import>

<body>

	<c:choose>
		<c:when test="${empty sessionScope.listeCommandes}">
			<p class="erreur"> Aucune commande enrégistrée. </p>
		</c:when>
		<c:otherwise>
			<table>
				<thead>
					<tr>
						<th>Client</th>
						<th>Date</th>
						<th>Montant</th>
						<th>Mode de paiement</th>
						<th>Statut de paiement</th>
						<th>Mode de livraison</th>
						<th>Statut de livraison</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sessionScope.listeCommandes}" var="element" varStatus="status">
					<c:set var="couleur">impair</c:set>
						<c:if test="${status.count mod 2 == 0}">
							<c:set var="couleur" value="pair"/>
						</c:if>	
						<tr class="${couleur}">
							<td><c:out value="${element.value.client.nom} "></c:out><c:out value=" ${element.value.client.prenom}"></c:out>  </td>
							<td><c:out value="${element.value.date}"></c:out></td>
							<td><c:out value="${element.value.montant}"></c:out></td>
							<td><c:out value="${element.value.modePaiement}"></c:out></td>
							<td><c:out value="${element.value.statutPaiement}"></c:out></td>
							<td><c:out value="${element.value.modeLivraison}"></c:out></td>
							<td><c:out value="${element.value.statutLivraison }"></c:out>
	 						<td class="supprimer"> <a href='<c:url value="/suppressionCommande"><c:param name="dateCommande" value="${element.value.date}"/> </c:url>'>
							 <img alt="supprimer" src='<c:url value ="/inc/supprimer.png"></c:url>'></a> </td>
						</tr>
					</c:forEach>		
				</tbody>
			</table>	
		</c:otherwise>
	
	</c:choose>


</body>
</html>