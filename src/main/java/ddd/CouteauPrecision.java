package ddd;

/**
 * Produit Couteau de Précision dans le système DDD
 */
public class CouteauPrecision extends EnsembleBricolageABC {

    public CouteauPrecision() {
        super();
    }

    public CouteauPrecision(String nom, double prix, String type, String details) {
        super(nom, prix, type, details);
    }

    @Override
    public void afficherInformations() {
        System.out.println("=== COUTEAU DE PRÉCISION ===");
        System.out.println("Nom: " + getNom());
        System.out.println("Prix: " + getPrix() + " $");
        System.out.println("Type: " + getType());
        System.out.println("Détails: " + getDetails());
        System.out.println("============================");
    }
}
