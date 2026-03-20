package ddd;

public class ProduitGeneriqueDDD extends EnsembleBricolageABC {

    public ProduitGeneriqueDDD() { super(); }

    public ProduitGeneriqueDDD(String nom, double prix, String type, String details) {
        super(nom, prix, type, details);
    }

    @Override
    public void afficherInformations() {
        System.out.println("=== " + getNom().toUpperCase() + " ===");
        System.out.println("Nom: " + getNom());
        System.out.println("Prix: " + getPrix() + " $");
        System.out.println("Type: " + getType());
        System.out.println("Détails: " + getDetails());
        System.out.println("=".repeat(getNom().length() + 8));
    }
}
