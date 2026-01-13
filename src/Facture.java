import java.util.ArrayList;

// Classe pour gérer la facture
public class Facture {
    private Client client;
    private ArrayList<Article> articles;
    private static final double TAUX_TVA = 0.16;
    private static final double TAUX_REMISE = 0.30;
    private static final double SEUIL_REMISE = 100.0;
    
    // Constructeur
    public Facture(Client client) {
        this.client = client;
        this.articles = new ArrayList<Article>();
    }
    
    // Ajouter un article
    public void ajouterArticle(Article article) {
        articles.add(article);
    }
    
    // Calculer le total hors taxe
    public double calculerTotalHT() {
        double total = 0;
        for (Article article : articles) {
            total += article.getTotal();
        }
        return total;
    }
    
    // Calculer la remise
    public double calculerRemise() {
        double totalHT = calculerTotalHT();
        if (totalHT >= SEUIL_REMISE) {
            return totalHT * TAUX_REMISE;
        }
        return 0;
    }
    
    // Calculer la TVA
    public double calculerTVA() {
        double totalHT = calculerTotalHT();
        double remise = calculerRemise();
        return (totalHT - remise) * TAUX_TVA;
    }
    
    // Calculer le net à payer
    public double calculerNetAPayer() {
        double totalHT = calculerTotalHT();
        double remise = calculerRemise();
        double tva = calculerTVA();
        return totalHT - remise + tva;
    }
    
    // Getters
    public Client getClient() {
        return client;
    }
    
    public ArrayList<Article> getArticles() {
        return articles;
    }
}
