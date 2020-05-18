package com.tp.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.tp.beans.Client;
import com.tp.beans.Commande;

public class CreationCommandeForm {
    public static final String CHAMP_NOM = "nomClient";
    public static final String CHAMP_DATE = "dateCommande";
    public static final String CHAMP_MONTANT = "montantCommande";
    public static final String CHAMP_MODE_PAIEMENT = "modePaiementCommande";
    public static final String CHAMP_STATUT_PAIEMENT = "statutPaiementCommande";
    public static final String CHAMP_MODE_LIVRAISON = "modeLivraisonCommande";
    public static final String CHAMP_STATUT_LIVRAISON = "statutLivraisonCommande";
    public static final String FORMAT_DATE = "dd/MM/yyyy HH:mm:ss";
    public static final String ATT_LISTE_CLIENTS = "listeClients";
    public static final String ATT_LISTE_COMMANDES = "listeCommandes";

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
	    this.resultat = "Succ�s de la cr�ation commande!";
	else
	    this.resultat = "Echec de la cr�ation commande !";
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

    private void validationMontant(String montant) throws Exception {
	try {
	    Double valeur = Double.parseDouble(montant);
	    if (valeur < 0)
		throw new Exception("Le montant sp�cifi� doit �tre positif");
	} catch (NumberFormatException e) {
	    throw new Exception("Ce champ  ne doit contenir que des chiffres");
	} catch (NullPointerException e) {
	    throw new Exception("Le montant de la commande doit �tre sp�cifi�");
	}
    }

    private void validation(String champ) throws Exception {
	if (champ != null && champ.length() < 2)
	    throw new Exception("Ce champ doit contenir au moins deux caract�res");
    }

    private void validationStatutPaiement(String statut) throws Exception {
	validation(statut);
    }

    private void validationStatutLivraison(String statut) throws Exception {
	validation(statut);
    }

    private void validationModePaiement(String mode) throws Exception {
	if (mode == null)
	    throw new Exception("Le mode de paiement doit �tre renseign�");
	else
	    validation(mode);
    }

    private void validationModeLivraison(String mode) throws Exception {
	if (mode == null)
	    throw new Exception("Le mode de Livraison doit �tre renseign�");
	else
	    validation(mode);
    }

    private void setErreur(String champ, String message) {
	erreurs.put(champ, message);
    }

    private String creationDate() {
	/* R�cup�ration de la date courante */
	DateTime dt = new DateTime();

	/* Conversion de la date en String selon le format d�fini */
	DateTimeFormatter formatter = DateTimeFormat.forPattern(FORMAT_DATE);

	String date = dt.toString(formatter);
	return date;
    }

    /*
     * Inscris un client gr�ce aux donn�es des champs necessaires � la cr�ation de
     * client dans la requ�te. R�cup�re les donn�es des champs commandes, les
     * v�rifie et les mets dans un objet Commande. Renvoie l'objet Commande cr�e �
     * partir des donn�es.
     */
    public Commande passerCommande(HttpServletRequest request) {
	/* Inscription du client */
	CreationClientForm formulaire_client = new CreationClientForm();
	Client client = formulaire_client.inscrireClient(request);

	erreurs = formulaire_client.getErreurs();
	/*
	 * On travaille avec le m�me Map d'erreurs du formulaire de cr�ation du client
	 */

	/* R�cup�ration des donn�es des champs de la commande */

	String montant = getChamp(request, CHAMP_MONTANT);
	String modeLivraison = getChamp(request, CHAMP_MODE_LIVRAISON);
	String modePaiement = getChamp(request, CHAMP_MODE_PAIEMENT);
	String statutLivraison = getChamp(request, CHAMP_STATUT_LIVRAISON);
	String statutPaiement = getChamp(request, CHAMP_STATUT_PAIEMENT);
	String date = creationDate();

	try {
	    validationMontant(montant);
	} catch (Exception e) {
	    setErreur("montant", e.getMessage());
	}

	try {
	    validationModeLivraison(modeLivraison);
	} catch (Exception e) {
	    setErreur("modeLivraison", e.getMessage());
	}

	try {
	    validationModePaiement(modePaiement);
	} catch (Exception e) {
	    setErreur("modePaiement", e.getMessage());
	}

	try {
	    validationStatutLivraison(statutLivraison);
	} catch (Exception e) {
	    setErreur("statutLivraison", e.getMessage());
	}

	try {
	    validationStatutPaiement(statutPaiement);
	} catch (Exception e) {
	    setErreur("StatutPaiement", e.getMessage());
	}

	setSuccess();
	setResultat();
	Commande commande = new Commande();
	commande.setClient(client);
	commande.setDate(date);
	commande.setModePaiement(modePaiement);
	commande.setStatutPaiement(statutPaiement);
	commande.setModeLivraison(modeLivraison);
	commande.setStatutLivraison(statutLivraison);

	/*
	 * le montant n'est assign� � la commande que si sa saisie ne contient pas
	 * d'erreurs
	 */
	if (erreurs.get("montant") == null)
	    commande.setMontant(Double.parseDouble(montant));

	return commande;

    }

    @SuppressWarnings("unchecked")
    public boolean verificationExistanceClientEnSession(HttpServletRequest request, HttpSession session) {
	String nomClient = getChamp(request, CHAMP_NOM);
	Map<String, Client> listeClients = null;
	if (session.getAttribute(ATT_LISTE_CLIENTS) instanceof HashMap<?, ?>)
	    listeClients = (HashMap<String, Client>) session.getAttribute(ATT_LISTE_CLIENTS);
	/*
	 * Si la liste et le nom existe, on teste l'appartenance du nom � la liste en
	 * session
	 */
	if (nomClient != null && listeClients != null) {
	    if (listeClients.get(nomClient) != null)
		return true;
	}
	return false;
    }

    @SuppressWarnings("unchecked")
    public void ajouterClientDansLaListeEnSession(HttpSession session, Commande commande,
	    Map<String, Client> mapClients, boolean isClientExistDansLaSession) {
	if (!isClientExistDansLaSession) {
	    if (session.getAttribute(ATT_LISTE_CLIENTS) == null) {
		mapClients.put(commande.getClient().getNom(), commande.getClient());
		session.setAttribute(ATT_LISTE_CLIENTS, mapClients);
	    } else {
		if (session.getAttribute(ATT_LISTE_CLIENTS) instanceof HashMap<?, ?>)
		    mapClients = (HashMap<String, Client>) session.getAttribute(ATT_LISTE_CLIENTS);
		mapClients.put(commande.getClient().getNom(), commande.getClient());
	    }
	}

    }

    public void ajouterCommandeEnSession(HttpSession session, Commande commande, Map<String, Commande> mapCommandes) {
	mapCommandes.put(commande.getDate(), commande);
	if (session.getAttribute(ATT_LISTE_COMMANDES) == null)
	    session.setAttribute(ATT_LISTE_COMMANDES, mapCommandes);
    }
}
