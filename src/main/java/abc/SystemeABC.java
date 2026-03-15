package abc;

import utils.Logger;

/**
 * Mock du système ABC - génère des rapports au format JSON
 */
public class SystemeABC {
    private Logger logger;

    public SystemeABC() {
        this.logger = Logger.getInstance();
        logger.info("SystemeABC initialisé");
    }

    /**
     * Génère un rapport pour un produit donné au format JSON
     * @param idProduit ID du produit (1=Super colle, 2=Couteau précision, 3=Tablette humide)
     * @return Chaîne JSON représentant le produit
     */
    public String genererRapport(int idProduit) {
        logger.info("Génération du rapport pour le produit ID: " + idProduit);

        String rapport = null;

        switch (idProduit) {
            case 1:
                rapport = "{\"nom\": \"Super colle\", \"volume_ml\": 20, \"temps_sechage_sec\": 30, \"resistant_eau\": true, \"prix\": 4.99}";
                break;
            case 2:
                rapport = "{\"nom\": \"Couteau de précision\", \"longueur_lame_cm\": 2.5, \"lame_remplacable\": true, \"materiau\": \"Acier inoxydable\", \"prix\": 9.49}";
                break;
            case 3:
                rapport = "{\"nom\": \"Tablette humide\", \"taille\": \"Standard\", \"feuilles_supplémentaires\": 20, \"material\": \"plastique\", \"prix\": 40.00}";
                break;
            default:
                logger.error("Produit inexistant avec ID: " + idProduit);
                throw new IllegalArgumentException("ID de produit invalide: " + idProduit);
        }

        logger.info("Rapport généré avec succès pour: " + idProduit);
        return rapport;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
