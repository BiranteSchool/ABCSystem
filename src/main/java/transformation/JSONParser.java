package transformation;

import utils.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser JSON manuel (sans API externe) pour extraire les données
 */
public class JSONParser {
    private Logger logger;

    public JSONParser() {
        this.logger = Logger.getInstance();
    }

    /**
     * Parse une chaîne JSON et retourne un Map clé-valeur
     * @param json Chaîne JSON à parser
     * @return Map contenant les paires clé-valeur
     */
    public Map<String, String> parse(String json) {
        logger.info("Début du parsing JSON");
        Map<String, String> result = new HashMap<>();

        try {
            String cleaned = json.trim();
            if (cleaned.startsWith("{") && cleaned.endsWith("}")) {
                cleaned = cleaned.substring(1, cleaned.length() - 1);
            }

            Pattern pattern = Pattern.compile("\"([^\"]+)\"\\s*:\\s*(\"([^\"]*)\"|([^,}]+))");
            Matcher matcher = pattern.matcher(cleaned);

            while (matcher.find()) {
                String key = matcher.group(1);
                String value = matcher.group(3) != null ? matcher.group(3) : matcher.group(4).trim();
                result.put(key, value);
                logger.info("Clé extraite: " + key + " = " + value);
            }

            logger.info("Parsing JSON terminé avec succès - " + result.size() + " éléments extraits");
        } catch (Exception e) {
            logger.exception("Erreur lors du parsing JSON", e);
            throw new RuntimeException("Erreur de parsing JSON", e);
        }

        return result;
    }

    /**
     * Extrait une valeur String du Map
     */
    public String getString(Map<String, String> data, String key) {
        return data.getOrDefault(key, "");
    }

    /**
     * Extrait une valeur double du Map
     */
    public double getDouble(Map<String, String> data, String key) {
        try {
            return Double.parseDouble(data.getOrDefault(key, "0.0"));
        } catch (NumberFormatException e) {
            logger.warning("Impossible de parser le double pour la clé: " + key);
            return 0.0;
        }
    }

    /**
     * Extrait une valeur int du Map
     */
    public int getInt(Map<String, String> data, String key) {
        try {
            return Integer.parseInt(data.getOrDefault(key, "0"));
        } catch (NumberFormatException e) {
            logger.warning("Impossible de parser l'entier pour la clé: " + key);
            return 0;
        }
    }

    /**
     * Extrait une valeur boolean du Map
     */
    public boolean getBoolean(Map<String, String> data, String key) {
        return Boolean.parseBoolean(data.getOrDefault(key, "false"));
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
