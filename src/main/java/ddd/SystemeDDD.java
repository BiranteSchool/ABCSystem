package ddd;

import transformation.ArticleExterneInterface;
import utils.Logger;
import java.util.ArrayList;
import java.util.List;

/**
 * Système DDD - importe les articles externes
 */
public class SystemeDDD {
    private List<EnsembleBricolageABC> catalogue;
    private Logger logger;

    public SystemeDDD() {
        this.catalogue = new ArrayList<>();
        this.logger = Logger.getInstance();
        logger.info("SystemeDDD initialisé");
    }

    /**
     * Importe un article externe dans le système DDD
     * @param article Article conforme à l'interface ArticleExterneInterface
     */
    public void importExtArt(ArticleExterneInterface article) {
        logger.info("Importation d'un article externe: " + article.getNom());

        try {
            EnsembleBricolageABC produit = null;

            String nom = article.getNom();
            if (nom.contains("Super colle")) {
                produit = new SuperColle(
                    article.getNom(),
                    article.getPrix(),
                    article.getType(),
                    article.getDetails()
                );
            } else if (nom.contains("Couteau de précision")) {
                produit = new CouteauPrecision(
                    article.getNom(),
                    article.getPrix(),
                    article.getType(),
                    article.getDetails()
                );
            } else if (nom.contains("Tablette humide")) {
                produit = new TabletteHumide(
                    article.getNom(),
                    article.getPrix(),
                    article.getType(),
                    article.getDetails()
                );
            } else {
                // Cas général : produit non spécifique → ProduitGeneriqueDDD
                produit = new ProduitGeneriqueDDD(
                    article.getNom(),
                    article.getPrix(),
                    article.getType(),
                    article.getDetails()
                );
                logger.info("Produit générique créé pour: " + nom);
            }

            catalogue.add(produit);
            logger.info("Article importé avec succès dans le catalogue DDD: " + article.getNom());

        } catch (Exception e) {
            logger.exception("Erreur lors de l'importation de l'article", e);
            throw e;
        }
    }

    /**
     * Affiche tous les articles du catalogue
     */
    public void afficherCatalogue() {
        logger.info("Affichage du catalogue DDD - " + catalogue.size() + " article(s)");
        System.out.println("\n========== CATALOGUE DDD ==========");
        System.out.println("Nombre d'articles: " + catalogue.size());
        System.out.println("===================================\n");

        for (EnsembleBricolageABC article : catalogue) {
            article.afficherInformations();
            System.out.println();
        }
    }

    public List<EnsembleBricolageABC> getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(List<EnsembleBricolageABC> catalogue) {
        this.catalogue = catalogue;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
