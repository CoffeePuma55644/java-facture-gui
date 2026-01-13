import java.util.ArrayList;

public class Facture {
    private Client client;
    private ArrayList<Article> articles;
    private static final double TAUX_TVA = 0.16;
    private static final double TAUX_REMISE = 0.30;
    private static final double SEUIL_REMISE = 100.0;
    
    public Facture(Client client) {
        this.client = client;
        this.articles = new ArrayList<Article>();
    }
    
    public void ajouterArticle(Article article) {
        articles.add(article);
    }
    
    public double calculerTotalHT() {
        double total = 0;
        for (Article article : articles) {
            total += article.getPrixTotal();
        }
        return total;
    }
    
    public double calculerRemise() {
        double totalHT = calculerTotalHT();
        if (totalHT >= SEUIL_REMISE) {
            return totalHT * TAUX_REMISE;
        }
        return 0;
    }
    
    public double calculerTVA() {
        double totalHT = calculerTotalHT();
        double remise = calculerRemise();
        return (totalHT - remise) * TAUX_TVA;
    }
    
    public double calculerNetAPayer() {
        double totalHT = calculerTotalHT();
        double remise = calculerRemise();
        double tva = calculerTVA();
        return totalHT - remise + tva;
    }
    
    public Client getClient() {
        return client;
    }
    
    public ArrayList<Article> getArticles() {
        return articles;
    }
}
