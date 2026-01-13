public class TestFacture {
    public static void main(String[] args) {
        System.out.println("=== Test du Système de Facturation ===\n");
        
        // Test 1: Facture sans remise (Total HT < 100$)
        System.out.println("Test 1: Facture sans remise");
        Client client1 = new Client("Jean Dupont", "0123456789");
        Facture facture1 = new Facture(client1);
        
        Article article1 = new Article("Pain", 2, 15.0);
        Article article2 = new Article("Lait", 3, 10.0);
        facture1.ajouterArticle(article1);
        facture1.ajouterArticle(article2);
        
        System.out.println("Client: " + client1.getNom());
        System.out.println("Total HT: " + facture1.calculerTotalHT() + " $");
        System.out.println("Remise: " + facture1.calculerRemise() + " $");
        System.out.println("Total HT après remise: " + facture1.calculerTotalHTApresRemise() + " $");
        System.out.println("TVA (16%): " + facture1.calculerTVA() + " $");
        System.out.println("Net à payer: " + facture1.calculerNetAPayer() + " $");
        System.out.println("Attendu: 69.6 $ (60 * 1.16)\n");
        
        // Test 2: Facture avec remise (Total HT >= 100$)
        System.out.println("Test 2: Facture avec remise");
        Client client2 = new Client("Marie Martin", "9876543210");
        Facture facture2 = new Facture(client2);
        
        Article article3 = new Article("Ordinateur", 1, 120.0);
        facture2.ajouterArticle(article3);
        
        System.out.println("Client: " + client2.getNom());
        System.out.println("Total HT: " + facture2.calculerTotalHT() + " $");
        System.out.println("Remise (30%): " + facture2.calculerRemise() + " $");
        System.out.println("Total HT après remise: " + facture2.calculerTotalHTApresRemise() + " $");
        System.out.println("TVA (16%): " + facture2.calculerTVA() + " $");
        System.out.println("Net à payer: " + facture2.calculerNetAPayer() + " $");
        System.out.println("Attendu: 97.44 $ (120 - 36 = 84, puis 84 * 1.16 = 97.44)\n");
        
        // Test 3: Facture exactement à 100$
        System.out.println("Test 3: Facture exactement à 100$ (avec remise)");
        Client client3 = new Client("Paul Durand", "5555555555");
        Facture facture3 = new Facture(client3);
        
        Article article4 = new Article("Chaise", 4, 25.0);
        facture3.ajouterArticle(article4);
        
        System.out.println("Client: " + client3.getNom());
        System.out.println("Total HT: " + facture3.calculerTotalHT() + " $");
        System.out.println("Remise (30%): " + facture3.calculerRemise() + " $");
        System.out.println("Total HT après remise: " + facture3.calculerTotalHTApresRemise() + " $");
        System.out.println("TVA (16%): " + facture3.calculerTVA() + " $");
        System.out.println("Net à payer: " + facture3.calculerNetAPayer() + " $");
        System.out.println("Attendu: 81.2 $ (100 - 30 = 70, puis 70 * 1.16 = 81.2)\n");
        
        System.out.println("=== Tests terminés ===");
    }
}
