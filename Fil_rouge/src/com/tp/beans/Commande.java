package com.tp.beans;

public class Commande {
    private Client client;
    private String modePaiement, statutPaiement, modeLivraison, statutLivraison, date;
    private double montant;

    public String getDate() {
        return date;
    }

    public void setDate( String date ) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient( Client client ) {
        this.client = client;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement( String modeDePaiement ) {
        this.modePaiement = modeDePaiement;
    }

    public String getStatutPaiement() {
        return statutPaiement;
    }

    public void setStatutPaiement( String statutDuPaiement ) {
        this.statutPaiement = statutDuPaiement;
    }

    public String getModeLivraison() {
        return modeLivraison;
    }

    public void setModeLivraison( String modeDeLivraison ) {
        this.modeLivraison = modeDeLivraison;
    }

    public String getStatutLivraison() {
        return statutLivraison;
    }

    public void setStatutLivraison( String statutDeLivraison ) {
        this.statutLivraison = statutDeLivraison;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant( double montant ) {
        this.montant = montant;
    }

}
