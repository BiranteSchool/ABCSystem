package ddd;

/**
 * Produit Super Colle dans le système DDD
 */
public class SuperColle extends EnsembleBricolageABC {

    public SuperColle() {
        super();
    }

    public SuperColle(String nom, double prix, String type, String details) {
        super(nom, prix, type, details);
    }

    @Override
    public void afficherInformations() {
        System.out.println("=== SUPER COLLE ===");
        System.out.println("Nom: " + getNom());
        System.out.println("Prix: " + getPrix() + " $");
        System.out.println("Type: " + getType());
        System.out.println("Détails: " + getDetails());
        System.out.println("===================");
    }
}
