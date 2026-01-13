// Classe pour représenter un article
public class Article {
    private String nom;
    private int quantite;
    private double prixUnitaire;
    
    // Constructeur
    public Article(String nom, int quantite, double prixUnitaire) {
        this.nom = nom;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
    }
    
    // Getters
    public String getNom() {
        return nom;
    }
    
    public int getQuantite() {
        return quantite;
    }
    
    public double getPrixUnitaire() {
        return prixUnitaire;
    }
    
    // Calculer le total pour cet article
    public double getTotal() {
        return quantite * prixUnitaire;
    }
}
