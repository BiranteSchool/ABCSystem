package transformation;

import utils.Logger;
import java.util.Map;
import java.util.List;

public class AdaptateurGenerique implements ArticleExterneInterface {
    private String nom;
    private double prix;
    private String type;
    private String details;
    private Logger logger;

    public AdaptateurGenerique(String jsonData, String type, List<String> cleDetails) {
        this.logger = Logger.getInstance();
        JSONParser parser = new JSONParser();
        Map<String, String> data = parser.parse(jsonData);

        this.nom = parser.getString(data, "nom");
        this.prix = parser.getDouble(data, "prix");
        this.type = type;

        StringBuilder sb = new StringBuilder();
        for (String cle : cleDetails) {
            sb.append(cle).append(": ").append(data.getOrDefault(cle, "N/A")).append(", ");
        }
        this.details = sb.toString().replaceAll(", $", "");
    }

    @Override public String getNom() { return nom; }
    @Override public double getPrix() { return prix; }
    @Override public String getType() { return type; }
    @Override public String getDetails() { return details; }
}
