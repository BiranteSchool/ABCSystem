package transformation;

import utils.Logger;
import java.util.Map;

/**
 * Adaptateur pour le produit Couteau de Précision (pattern Adapter)
 */
public class AdaptateurCouteauPrecision implements ArticleExterneInterface {
    private String nom;
    private double longueurLameCm;
    private boolean lameRemplacable;
    private String materiau;
    private double prix;
    private Logger logger;

    public AdaptateurCouteauPrecision(String jsonData) {
        this.logger = Logger.getInstance();
        logger.info("Création d'un AdaptateurCouteauPrecision");

        JSONParser parser = new JSONParser();
        Map<String, String> data = parser.parse(jsonData);

        this.nom = parser.getString(data, "nom");
        this.longueurLameCm = parser.getDouble(data, "longueur_lame_cm");
        this.lameRemplacable = parser.getBoolean(data, "lame_remplacable");
        this.materiau = parser.getString(data, "materiau");
        this.prix = parser.getDouble(data, "prix");

        logger.info("AdaptateurCouteauPrecision créé pour: " + this.nom);
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public double getPrix() {
        return prix;
    }

    @Override
    public String getType() {
        return "Ensemble de bricolage ABC - Outil de coupe";
    }

    @Override
    public String getDetails() {
        return String.format("Longueur lame: %.1f cm, Lame remplaçable: %s, Matériau: %s",
                longueurLameCm, lameRemplacable ? "Oui" : "Non", materiau);
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getLongueurLameCm() {
        return longueurLameCm;
    }

    public void setLongueurLameCm(double longueurLameCm) {
        this.longueurLameCm = longueurLameCm;
    }

    public boolean isLameRemplacable() {
        return lameRemplacable;
    }

    public void setLameRemplacable(boolean lameRemplacable) {
        this.lameRemplacable = lameRemplacable;
    }

    public String getMateriau() {
        return materiau;
    }

    public void setMateriau(String materiau) {
        this.materiau = materiau;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
