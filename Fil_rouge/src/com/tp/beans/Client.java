package com.tp.beans;

public class Client {
	private String nom, prenom, adresse, telephone, email;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresseDeLivraison) {
		this.adresse = adresseDeLivraison;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String numeroDeTelephone) {
		this.telephone = numeroDeTelephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String adresseEmail) {
		this.email = adresseEmail;
	}

}
