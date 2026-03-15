package transformation;

import utils.Logger;
import java.util.Map;

/**
 * Adaptateur pour le produit Super Colle (pattern Adapter)
 */
public class AdaptateurSuperColle implements ArticleExterneInterface {
    private String nom;
    private int volumeMl;
    private int tempsSechageSec;
    private boolean resistantEau;
    private double prix;
    private Logger logger;

    public AdaptateurSuperColle(String jsonData) {
        this.logger = Logger.getInstance();
        logger.info("Création d'un AdaptateurSuperColle");

        JSONParser parser = new JSONParser();
        Map<String, String> data = parser.parse(jsonData);

        this.nom = parser.getString(data, "nom");
        this.volumeMl = parser.getInt(data, "volume_ml");
        this.tempsSechageSec = parser.getInt(data, "temps_sechage_sec");
        this.resistantEau = parser.getBoolean(data, "resistant_eau");
        this.prix = parser.getDouble(data, "prix");

        logger.info("AdaptateurSuperColle créé pour: " + this.nom);
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
        return "Ensemble de bricolage ABC - Adhésif";
    }

    @Override
    public String getDetails() {
        return String.format("Volume: %d ml, Temps séchage: %d sec, Résistant eau: %s",
                volumeMl, tempsSechageSec, resistantEau ? "Oui" : "Non");
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getVolumeMl() {
        return volumeMl;
    }

    public void setVolumeMl(int volumeMl) {
        this.volumeMl = volumeMl;
    }

    public int getTempsSechageSec() {
        return tempsSechageSec;
    }

    public void setTempsSechageSec(int tempsSechageSec) {
        this.tempsSechageSec = tempsSechageSec;
    }

    public boolean isResistantEau() {
        return resistantEau;
    }

    public void setResistantEau(boolean resistantEau) {
        this.resistantEau = resistantEau;
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
