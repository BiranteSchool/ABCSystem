package transformation;

/**
 * Interface que le système DDD accepte pour importer des articles externes
 */
public interface ArticleExterneInterface {
    String getNom();
    double getPrix();
    String getType();
    String getDetails();
}
