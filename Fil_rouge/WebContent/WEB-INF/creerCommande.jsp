<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Création d'une commande</title>
<link type="text/css" rel="stylesheet"
	href=<c:url value="/inc/style.css"/> />
</head>

<c:import url="/inc/menu.jsp"></c:import>

<body>
	<div>
		<form method="post" action=<c:url value="/creationCommande"/>>
			<%-- Petite astuce ici : placer le client accessible via  
              l'EL ${ commande.client } dans une variable "client" de 
              portée request, pour que le code du fragment fonctionne
              à la fois depuis le formulaire de création d'un client 
              et depuis celui de création d'une commande. --%>
              
              <!-- Ce test Gère donc trois types de vues au niveau de l'import qui suit:
               test négatif -> une vue avec doGEt(affichage simple du formulaire avec client existant)
               test positif -> vue avec doPost.(affichage après saisie ou avec nouveau client)
               fait appel à ${commande.client} -->
              
            <c:if test="${empty requestScope.client}">
            	<c:set var="client" value="${commande.client}" scope="request"></c:set>
            </c:if> 
			
            <c:import url="/inc/inc_client_form.jsp"></c:import>
            
			<fieldset>
				<legend>Informations commande</legend>

				<label for="dateCommande">Date <span class="requis">*</span></label>
				<input type="text" id="dateCommande" name="dateCommande" value='<c:out value="${commande.date}"></c:out>'
					size="20" maxlength="20" disabled /> <br>						
					
				<label for="montantCommande"> Montant <span class="requis">*</span></label>
				<input type="text" id="montantCommande" name="montantCommande"
					value='<c:out value="${param.montantCommande}"></c:out>' size="20" maxlength="20" />
				<span class="erreur"><c:out value="${formulaire.erreurs.montant}"></c:out></span>	 <br />
				
				<label for="modePaiementCommande">Mode de paiement <span class="requis">*</span></label> 
				<input type="text"
					id="modePaiementCommande" name="modePaiementCommande" value='<c:out value="${commande.modePaiement}"></c:out>'
					size="20" maxlength="20" /> 
				<span class="erreur"><c:out value="${formulaire.erreurs.modePaiement}"></c:out></span> <br />
				
				<label for="statutPaiementCommande">Statut du paiement</label>
				<input
					type="text" id="statutPaiementCommande"
					name="statutPaiementCommande" value='<c:out value="${commande.statutPaiement}"></c:out>' size="20" maxlength="20" />
				
				<span class="erreur"><c:out value="${formulaire.erreurs.statutPaiement}"></c:out></span>	<br /> 
				
				
				<label for="modeLivraisonCommande">Mode de livraison
					<span class="requis">*</span></label>
				<input type="text" id="modeLivraisonCommande"
					name="modeLivraisonCommande" value='<c:out value="${commande.modeLivraison}"></c:out>' size="20" maxlength="20" />
				<span class="erreur"> <c:out value="${formulaire.erreurs.modeLivraison}"></c:out></span>	<br /> 
				
				
				<label for="statutLivraisonCommande">Statut de la livraison</label>
				<input type="text" id="statutLivraisonCommande"
					name="statutLivraisonCommande" value='<c:out value="${commande.statutLivraison}"></c:out>' size="20" maxlength="20" />
				<span class="erreur"><c:out value="${formulaire.erreurs.statutLivraison}"></c:out></span> <br />
			
			</fieldset>
			<input type="submit" value="Valider" /> <input type="reset"
				value="Remettre à zéro" /> <br />
		</form>
		<p class="info">
			${formulaire.resultat}
		</p>
	</div>
</body>
</html>