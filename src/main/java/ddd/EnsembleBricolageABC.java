package ddd;

/**
 * Classe abstraite représentant un Ensemble de bricolage ABC dans le système DDD
 */
public abstract class EnsembleBricolageABC {
    private String nom;
    private double prix;
    private String type;
    private String details;

    public EnsembleBricolageABC() {
    }

    public EnsembleBricolageABC(String nom, double prix, String type, String details) {
        this.nom = nom;
        this.prix = prix;
        this.type = type;
        this.details = details;
    }

    public abstract void afficherInformations();

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
