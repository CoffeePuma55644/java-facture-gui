public class Client {
    private String nom;
    private String numeroTelephone;
    
    public Client(String nom, String numeroTelephone) {
        this.nom = nom;
        this.numeroTelephone = numeroTelephone;
    }
    
    public String getNom() {
        return nom;
    }
    
    public String getNumeroTelephone() {
        return numeroTelephone;
    }
}
