// Classe pour représenter un client
public class Client {
    private String nom;
    private String telephone;
    
    // Constructeur
    public Client(String nom, String telephone) {
        this.nom = nom;
        this.telephone = telephone;
    }
    
    // Getters
    public String getNom() {
        return nom;
    }
    
    public String getTelephone() {
        return telephone;
    }
}
