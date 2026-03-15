import abc.SystemeABC;
import ddd.SystemeDDD;
import transformation.AdaptateurSuperColle;
import transformation.AdaptateurCouteauPrecision;
import transformation.AdaptateurTabletteHumide;
import transformation.ArticleExterneInterface;
import utils.Logger;

/**
 * Classe principale démontrant le module de transformation ETL
 * Pattern Adapter pour connecter le système ABC au système DDD
 */
public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        logger.info("========================================");
        logger.info("DÉMARRAGE DU MODULE DE TRANSFORMATION");
        logger.info("Pattern: Adapter (ETL - Extract Transform Load)");
        logger.info("========================================");

        try {
            // ÉTAPE 1: EXTRACTION - Initialisation du système source ABC
            logger.info("\n=== ÉTAPE 1: EXTRACTION DES DONNÉES ===");
            SystemeABC systemeABC = new SystemeABC();

            // Extraction des 3 produits ABC (format JSON)
            String jsonSuperColle = systemeABC.genererRapport(1);
            String jsonCouteau = systemeABC.genererRapport(2);
            String jsonTablette = systemeABC.genererRapport(3);

            logger.info("Extraction terminée - 3 produits extraits");

            // ÉTAPE 2: TRANSFORMATION - Conversion via les adaptateurs
            logger.info("\n=== ÉTAPE 2: TRANSFORMATION DES DONNÉES ===");

            ArticleExterneInterface articleSuperColle = new AdaptateurSuperColle(jsonSuperColle);
            logger.info("Transformation 1/3 complétée");

            ArticleExterneInterface articleCouteau = new AdaptateurCouteauPrecision(jsonCouteau);
            logger.info("Transformation 2/3 complétée");

            ArticleExterneInterface articleTablette = new AdaptateurTabletteHumide(jsonTablette);
            logger.info("Transformation 3/3 complétée");

            logger.info("Transformation terminée - 3 articles adaptés");

            // ÉTAPE 3: CHARGEMENT - Import dans le système cible DDD
            logger.info("\n=== ÉTAPE 3: CHARGEMENT DANS LE SYSTÈME DDD ===");
            SystemeDDD systemeDDD = new SystemeDDD();

            systemeDDD.importExtArt(articleSuperColle);
            systemeDDD.importExtArt(articleCouteau);
            systemeDDD.importExtArt(articleTablette);

            logger.info("Chargement terminé - 3 articles importés");

            // ÉTAPE 4: AFFICHAGE - Vérification de l'exactitude des données
            logger.info("\n=== ÉTAPE 4: VÉRIFICATION DES DONNÉES ===");
            systemeDDD.afficherCatalogue();

            logger.info("\n========================================");
            logger.info("MODULE DE TRANSFORMATION TERMINÉ AVEC SUCCÈS");
            logger.info("Tous les articles ont été traités correctement");
            logger.info("========================================");

        } catch (Exception e) {
            logger.exception("ERREUR FATALE dans le module de transformation", e);
            System.err.println("\nUne erreur s'est produite. Consultez les logs ci-dessus.");
        }
    }
}
