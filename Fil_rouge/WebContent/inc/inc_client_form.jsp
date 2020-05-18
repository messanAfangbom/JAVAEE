<fieldset>
	<legend>Informations client</legend>

	<label for="nomClient"> Nom <span class="requis">*</span> </label> 
	<input type="text" id="nomClient" name="nomClient" value="<c:out value="${client.nom}"></c:out>"
	 size="20"	maxlength="20" />
	<span class = "erreur">${ formulaire.erreurs['nom']}</span> <br /> 
		
	<label for="prenomClient">Prénom</label> 
	<input type="text" id="prenomClient" name="prenomClient" value="<c:out value="${client.prenom}"></c:out>" size="20"
		maxlength="20" />
	<span class = "erreur">${formulaire.erreurs.prenom}</span>  <br /> 
	
	<label for="adresseClient">Adresse de livraison <span class="requis">*</span> </label>
	<input type="text" id="adresseClient" name="adresseClient" value="<c:out value="${client.adresse}"></c:out>"
		size="20" maxlength="20" />
	<span class = "erreur"> ${formulaire.erreurs.adresse} </span>  <br /> 
		 
	<label for="telephoneClient">Numéro de téléphone <span class="requis">*</span> </label> 
	<input type="text" id="telephoneClient" name="telephoneClient"
		value='<c:out value="${client.telephone}"></c:out>' size="20" maxlength="20" />
	<span class="erreur"> ${formulaire.erreurs.telephone} </span> <br /> 
	
	<label for="emailClient">Adresse email</label>
	<input type="email" id="emailClient" name="emailClient" value='<c:out value="${client.email}"></c:out>'
		size="20" maxlength="60" />
	<span class="erreur"> ${formulaire.erreurs.email} </span>	 <br />
		
</fieldset>
<br />