package com.tp.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tp.beans.Client;

public class CreationClientForm {
    public static final String CHAMP_NOM = "nomClient";
    public static final String CHAMP_PRENOM = "prenomClient";
    public static final String CHAMP_ADRESSE = "adresseClient";
    public static final String CHAMP_TELEPHONE = "telephoneClient";
    public static final String CHAMP_EMAIL = "emailClient";

    private String resultat;
    private boolean success;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public boolean getSuccess() {
	return success;
    }

    public String getResultat() {
	return resultat;
    }

    public void setSuccess() {
	if (erreurs.isEmpty())
	    this.success = true;
	else
	    this.success = false;
    }

    public void setResultat() {
	if (erreurs.isEmpty())
	    this.resultat = "Succès de la création client !";
	else
	    this.resultat = "Echec de la création client !";
    }

    public Map<String, String> getErreurs() {
	return erreurs;
    }

    private String getChamp(HttpServletRequest request, String champ) {
	String valeur = request.getParameter(champ);
	if (valeur == null || valeur.trim().length() == 0)
	    return null;
	else
	    return valeur.trim();
    }

    private void validationNom(String nom) throws Exception {
	if ((nom != null && nom.trim().length() < 2) || nom == null)
	    throw new Exception("Le nom doit contenir au moins deux caractères");
    }

    private void validation_Prenom(String prenom) throws Exception {
	if (prenom != null && prenom.trim().length() < 2)
	    throw new Exception("Le prénom doit contenir au moins deux caractères");
    }

    private void validationAdresse(String adresse) throws Exception {
	if ((adresse != null && adresse.trim().length() < 10) || adresse == null)
	    throw new Exception("L'adresse doit contenir au moins dix caractères");
    }

    private void validationEmail(String email) throws Exception {
	if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
	    throw new Exception("Merci de saisir une adresse mail valide.");
	}

    }

    /*
     * si le champ téléphone est vide, renvoie excpetion Si le champ est non vide
     * mais avec un nombre de chiffre inférieur à 4, renvoie exception Si le champ
     * est non vide et contient autre chose que des chiffres (par ex: aaa, 04aas,)
     * avec espace renvoie exception
     */
    private void validationTelephone(String telephone) throws Exception {

	if (telephone != null) {
	    if (!telephone.replaceAll("\\s", "").matches("^\\d+$")) {
		throw new Exception("Le numéro de téléphone doit uniquement contenir des chiffres.");
	    } else if (telephone.length() < 4) {
		throw new Exception("Le numéro de téléphone doit contenir au moins 4 chiffres.");
	    }
	} else {
	    throw new Exception("Merci d'entrer un numéro de téléphone.");
	}
    }

    private void setErreur(String champ, String message) {
	erreurs.put(champ, message);
    }

    /*
     * Récupération des données saisies, envoyées en tant que paramètres de la
     * requête Post générée à la validation du formulaire et mis en relation avec un
     * objet Client
     */

    public Client inscrireClient(HttpServletRequest request) {
	String nom = getChamp(request, CHAMP_NOM);
	String prenom = getChamp(request, CHAMP_PRENOM);
	String telephone = getChamp(request, CHAMP_TELEPHONE);
	String email = getChamp(request, CHAMP_EMAIL);
	String adresse = getChamp(request, CHAMP_ADRESSE);
	Client client = new Client();
	try {
	    validationNom(nom);
	} catch (Exception e) {
	    setErreur("nom", e.getMessage());
	}
	try {
	    validation_Prenom(prenom);
	} catch (Exception e) {
	    setErreur("prenom", e.getMessage());
	}
	try {
	    validationTelephone(telephone);
	} catch (Exception e) {
	    setErreur("telephone", e.getMessage());
	}
	try {
	    validationAdresse(adresse);
	} catch (Exception e) {
	    setErreur("adresse", e.getMessage());
	}
	try {
	    validationEmail(email);
	} catch (Exception e) {
	    setErreur("email", e.getMessage());
	}
	client.setNom(nom);
	client.setPrenom(prenom);
	client.setTelephone(telephone);
	client.setAdresse(adresse);
	client.setEmail(email);

	setResultat();
	setSuccess();
	return client;
    }

    @SuppressWarnings("unchecked")
    public void inscrireListeClientEnSession(HttpSession session, String att_liste_Client,
	    Map<String, Client> listeClients, Client client) {
	/*
	 * Si nous avons une liste de client en session alors on inscrit directement le
	 * client dans cette liste Sinon on ajoute une nouvelle liste contenant le
	 * client
	 */
	if (session.getAttribute(att_liste_Client) == null) {
	    listeClients.put(client.getNom(), client);
	    session.setAttribute(att_liste_Client, listeClients);
	} else {
	    if (session.getAttribute(att_liste_Client) instanceof HashMap<?, ?>)
		listeClients = (HashMap<String, Client>) session.getAttribute(att_liste_Client);
	    listeClients.put(client.getNom(), client);
	}
    }

}
