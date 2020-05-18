package com.tp.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.beans.Client;
import com.tp.beans.Commande;
import com.tp.forms.CreationCommandeForm;

@WebServlet("/CreationCOmmande")
public class CreationCommande extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /* Constantes */

    public static final String ATT_FORMULAIRE = "formulaire";
    public static final String ATT_COMMANDE = "commande";
    public static final String ATT_VUE = "/WEB-INF/creerCommande.jsp";
    public static final String ATT_VUE_MODE = "/WEB-INF/modeCreationCommande.jsp";
    public static final String ATT_VUE_SUCCES = "/WEB-INF/afficherCommande.jsp";
    public static final String ATT_CLIENT_EXISTANT = "clientExistant";
    public static final String ATT_LISTE_CLIENTS = "listeClients";
    public static final String ATT_CLIENT = "client";
    private Map<String, Commande> mapCommandes = new HashMap<String, Commande>();
    private Map<String, Client> mapClients = new HashMap<String, Client>();

    @SuppressWarnings("unchecked")
    /*
     * Par défaut affiche la page de sélection du mode de saisie de la commande i.e
     * avec ou sans client existant. Après choix du mode affiche le formulaire de
     * création de commande
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	HttpSession session = request.getSession();
	String nouveauClient = "nouveauClient";

	/* Vérifie si le client est nouveau ou ancien */

	if (request.getParameter(ATT_CLIENT_EXISTANT) != null) {
	    if (request.getParameter(ATT_CLIENT_EXISTANT).equals(nouveauClient))
		this.getServletContext().getRequestDispatcher(ATT_VUE).forward(request, response);
	    else {

		/* Reécupère la liste de clients en session */
		Map<String, Client> listeClients = null;
		if (session.getAttribute(ATT_LISTE_CLIENTS) instanceof HashMap<?, ?>)
		    listeClients = (HashMap<String, Client>) session.getAttribute(ATT_LISTE_CLIENTS);

		/*
		 * Extraction et attribution du client dont le nom est envoyé dans le paramètre
		 * CLIENT_EXISTANT à la session
		 */
		String temp = (String) request.getParameter(ATT_CLIENT_EXISTANT);
		Client client = listeClients.get(temp);
		request.setAttribute(ATT_CLIENT, client);
		this.getServletContext().getRequestDispatcher(ATT_VUE).forward(request, response);

	    }
	}
	this.getServletContext().getRequestDispatcher(ATT_VUE_MODE).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();

	CreationCommandeForm form = new CreationCommandeForm();
	Commande commande = form.passerCommande(request);
	boolean isClientExistDansLaSession = form.verificationExistanceClientEnSession(request, session);

	request.setAttribute(ATT_FORMULAIRE, form);
	request.setAttribute(ATT_COMMANDE, commande);

	/* La commande n'est ajoutée à la session que si elle est valide */
	/*
	 * Si le client de la commande est un nouveau client, il est ajouté dans la
	 * liste clients en session.(Une nouvelle est crée si elle n'existe pas.
	 */
	if (form.getSuccess()) {
	    form.ajouterClientDansLaListeEnSession(session, commande, mapClients, isClientExistDansLaSession);
	    form.ajouterCommandeEnSession(session, commande, mapCommandes);
	}

	/*
	 * La vue afficherCommande.jsp n'est affichée que si la commande est valide
	 * Sinon on affiche la page de saisie avec messages erreurs sur les champs
	 * invalides
	 */

	if (form.getSuccess())
	    this.getServletContext().getRequestDispatcher(ATT_VUE_SUCCES).forward(request, response);
	else
	    this.getServletContext().getRequestDispatcher(ATT_VUE).forward(request, response);
    }

}