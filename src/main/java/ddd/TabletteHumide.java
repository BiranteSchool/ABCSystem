package ddd;

/**
 * Produit Tablette Humide dans le système DDD
 */
public class TabletteHumide extends EnsembleBricolageABC {

    public TabletteHumide() {
        super();
    }

    public TabletteHumide(String nom, double prix, String type, String details) {
        super(nom, prix, type, details);
    }

    @Override
    public void afficherInformations() {
        System.out.println("=== TABLETTE HUMIDE ===");
        System.out.println("Nom: " + getNom());
        System.out.println("Prix: " + getPrix() + " $");
        System.out.println("Type: " + getType());
        System.out.println("Détails: " + getDetails());
        System.out.println("=======================");
    }
}
