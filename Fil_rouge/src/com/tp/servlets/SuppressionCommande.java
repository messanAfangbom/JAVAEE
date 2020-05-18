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

/**
 * Servlet implementation class SuppressionCommande
 */
@WebServlet("/SuppressionCommande")
public class SuppressionCommande extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String ATT_LISTE_COMMANDE = "listeCommandes";
    public static final String ATT_CLE_COMMANDE = "dateCommande";
    public static final String VUE = "/listeCommandes";

    public SuppressionCommande() {
	super();
    }

    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	/*
	 * On r�cup�re la liste de commande pr�sente en session dans la var
	 * listeCommande, puis on enl�ve l'objet dont la cl� se trouve dans le param�tre
	 * de la requ�te � l'aide de la m�thode remove() des objets Map.
	 */
	Map<String, String> listeCommandes = null;
	HttpSession session = request.getSession();

	if (session.getAttribute(ATT_LISTE_COMMANDE) instanceof HashMap<?, ?>)
	    listeCommandes = (HashMap<String, String>) session.getAttribute(ATT_LISTE_COMMANDE);
	if (listeCommandes != null)
	    listeCommandes.remove(request.getParameter(ATT_CLE_COMMANDE));
	response.sendRedirect(request.getContextPath() + VUE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doGet(request, response);
    }

}
