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
import com.tp.forms.CreationClientForm;

@WebServlet("/CreationClient")
public class CreationClient extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /* Constantes */

    public static final String ATT_CLIENT = "client";
    public static final String ATT_LISTE_CLIENTS = "listeClients";
    public static final String ATT_FORMULAIRE = "formulaire";
    public static final String ATT_VUE = "/WEB-INF/creerClient.jsp";
    public static final String ATT_VUE_SUCCES = "/WEB-INF/afficherClient.jsp";
    private Map<String, Client> clients = new HashMap<String, Client>();

    public CreationClient() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	/* Transmission à la page JSP en charge de l'affichage des données */
	this.getServletContext().getRequestDispatcher(ATT_VUE).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	/* Récupère ou crée une session si elle n'existe pas. */
	HttpSession session = request.getSession();

	CreationClientForm form = new CreationClientForm();
	Client client = form.inscrireClient(request);
	/*
	 * Si les données du formulaire sont valides, alors on ajoute le client dans la
	 * liste des clients et on ajoute cette liste à la session
	 */
	if (form.getSuccess()) {
	    form.inscrireListeClientEnSession(session, ATT_LISTE_CLIENTS, clients, client);
	}
	request.setAttribute(ATT_CLIENT, client);
	request.setAttribute(ATT_FORMULAIRE, form);

	/*
	 * S'il n'y a pas d'erreur dans la création du client on affiche la page succèss
	 * sinon on affiche la page d'inscription avec tous les champs en précisant les
	 * erreurs
	 */

	if (form.getSuccess())
	    this.getServletContext().getRequestDispatcher(ATT_VUE_SUCCES).forward(request, response);
	else
	    this.getServletContext().getRequestDispatcher(ATT_VUE).forward(request, response);

    }

}
