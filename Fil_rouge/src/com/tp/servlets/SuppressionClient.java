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

/**
 * Servlet implementation class SuppressionClient
 */
@WebServlet("/SuppressionClient")
public class SuppressionClient extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String ATT_LISTE_CLIENTS = "listeClients";
    public static final String ATT_CLE_CLIENT = "nomClient";
    public static final String VUE = "/listeClients";

    public SuppressionClient() {
	super();

    }

    @SuppressWarnings("unchecked")

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	/*
	 * On récupère la liste de commande présente dans la session dans la var
	 * listeCommande, puis on enlève l'objet dont la clé se trouve dans le paramètre
	 * de la requête à l'aide de la méthode remove() des objets Map.
	 */

	HttpSession session = request.getSession();
	Map<String, Client> listeClients = null;

	if (session.getAttribute(ATT_LISTE_CLIENTS) instanceof HashMap<?, ?>)
	    listeClients = (HashMap<String, Client>) session.getAttribute(ATT_LISTE_CLIENTS);

	if (listeClients != null)
	    listeClients.remove(request.getParameter(ATT_CLE_CLIENT));
	response.sendRedirect(request.getContextPath() + VUE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doGet(request, response);
    }

}
