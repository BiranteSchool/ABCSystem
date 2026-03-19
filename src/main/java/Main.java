import abc.SystemeABC;
import ddd.SystemeDDD;
import transformation.AdaptateurSuperColle;
import transformation.AdaptateurCouteauPrecision;
import transformation.AdaptateurTabletteHumide;
import transformation.AdaptateurGenerique;
import transformation.ArticleExterneInterface;
import utils.Logger;
import java.util.Properties;
import java.util.List;
import java.util.Arrays;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        try {
            SystemeABC systemeABC = new SystemeABC();
            SystemeDDD systemeDDD = new SystemeDDD();

            // =============================================
            // ITÉRATION #1 : 3 produits spécifiques
            // =============================================
            logger.info("========================================");
            logger.info("ITÉRATION #1 - 3 PRODUITS SPÉCIFIQUES");
            logger.info("========================================");

            String jsonSuperColle = systemeABC.genererRapport(1);
            String jsonCouteau = systemeABC.genererRapport(2);
            String jsonTablette = systemeABC.genererRapport(3);

            systemeDDD.importExtArt(new AdaptateurSuperColle(jsonSuperColle));
            systemeDDD.importExtArt(new AdaptateurCouteauPrecision(jsonCouteau));
            systemeDDD.importExtArt(new AdaptateurTabletteHumide(jsonTablette));

            systemeDDD.afficherCatalogue();

            // =============================================
            // ITÉRATION #2 : Généralisation
            // =============================================
            logger.info("========================================");
            logger.info("ITÉRATION #2 - CAS GÉNÉRAL");
            logger.info("========================================");

            SystemeDDD systemeDDDGenerique = new SystemeDDD();

            Properties config = new Properties();
            java.io.InputStreamReader reader = new java.io.InputStreamReader(
                new java.io.FileInputStream("/workspaces/ABCSystem/src/main/resources/produits.properties"),
                java.nio.charset.StandardCharsets.UTF_8
            );
            config.load(reader);

            for (int i = 1; config.containsKey("produit." + i + ".type"); i++) {
                String json = systemeABC.genererRapport(i);
                String type = config.getProperty("produit." + i + ".type");
                List<String> cles = Arrays.asList(
                    config.getProperty("produit." + i + ".details").split(",")
                );
                systemeDDDGenerique.importExtArt(new AdaptateurGenerique(json, type, cles));
            }

            systemeDDDGenerique.afficherCatalogue();

        } catch (Exception e) {
            Logger.getInstance().exception("ERREUR FATALE", e);
        }
    }
}
