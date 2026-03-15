package transformation;

import utils.Logger;
import java.util.Map;

/**
 * Adaptateur pour le produit Tablette Humide (pattern Adapter)
 */
public class AdaptateurTabletteHumide implements ArticleExterneInterface {
    private String nom;
    private String taille;
    private int feuillesSupplementaires;
    private String material;
    private double prix;
    private Logger logger;

    public AdaptateurTabletteHumide(String jsonData) {
        this.logger = Logger.getInstance();
        logger.info("Création d'un AdaptateurTabletteHumide");

        JSONParser parser = new JSONParser();
        Map<String, String> data = parser.parse(jsonData);

        this.nom = parser.getString(data, "nom");
        this.taille = parser.getString(data, "taille");
        this.feuillesSupplementaires = parser.getInt(data, "feuilles_supplémentaires");
        this.material = parser.getString(data, "material");
        this.prix = parser.getDouble(data, "prix");

        logger.info("AdaptateurTabletteHumide créé pour: " + this.nom);
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
        return "Ensemble de bricolage ABC - Accessoire de peinture";
    }

    @Override
    public String getDetails() {
        return String.format("Taille: %s, Feuilles supplémentaires: %d, Matériau: %s",
                taille, feuillesSupplementaires, material);
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public int getFeuillesSupplementaires() {
        return feuillesSupplementaires;
    }

    public void setFeuillesSupplementaires(int feuillesSupplementaires) {
        this.feuillesSupplementaires = feuillesSupplementaires;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
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
