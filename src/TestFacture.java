public class TestFacture {
    public static void main(String[] args) {
        System.out.println("=== TEST DU SYSTÈME DE FACTURATION ===\n");
        
        // Test 1 : Total HT < 100$ (pas de remise)
        System.out.println("--- Test 1 : Total HT = 80$ (pas de remise) ---");
        Client client1 = new Client("Jean Dupont", "0601020304");
        Facture facture1 = new Facture(client1);
        facture1.ajouterArticle(new Article("Pain", 2, 10.0));
        facture1.ajouterArticle(new Article("Lait", 3, 20.0));
        
        System.out.println("Client: " + client1.getNom() + " - " + client1.getTelephone());
        System.out.println("Total HT: " + facture1.calculerTotalHT() + " $");
        System.out.println("Remise: " + facture1.calculerRemise() + " $");
        System.out.println("Total HT après remise: " + facture1.calculerTotalHTApresRemise() + " $");
        System.out.println("TVA (16%): " + facture1.calculerTVA() + " $");
        System.out.println("Net à payer: " + facture1.calculerNetAPayer() + " $");
        System.out.println();
        
        // Test 2 : Total HT >= 100$ (avec remise de 30%)
        System.out.println("--- Test 2 : Total HT = 120$ (avec remise 30%) ---");
        Client client2 = new Client("Marie Martin", "0612345678");
        Facture facture2 = new Facture(client2);
        facture2.ajouterArticle(new Article("Ordinateur", 1, 100.0));
        facture2.ajouterArticle(new Article("Souris", 2, 10.0));
        
        System.out.println("Client: " + client2.getNom() + " - " + client2.getTelephone());
        System.out.println("Total HT: " + facture2.calculerTotalHT() + " $");
        System.out.println("Remise (30%): " + facture2.calculerRemise() + " $");
        System.out.println("Total HT après remise: " + facture2.calculerTotalHTApresRemise() + " $");
        System.out.println("TVA (16%): " + facture2.calculerTVA() + " $");
        System.out.println("Net à payer: " + facture2.calculerNetAPayer() + " $");
        System.out.println();
        
        // Vérification des résultats attendus
        System.out.println("=== VÉRIFICATION DES RÉSULTATS ===");
        
        // Test 1 vérifications
        assert facture1.calculerTotalHT() == 80.0 : "Erreur Test 1 - Total HT";
        assert facture1.calculerRemise() == 0.0 : "Erreur Test 1 - Remise";
        assert facture1.calculerTotalHTApresRemise() == 80.0 : "Erreur Test 1 - Total HT après remise";
        assert Math.abs(facture1.calculerTVA() - 12.8) < 0.01 : "Erreur Test 1 - TVA";
        assert Math.abs(facture1.calculerNetAPayer() - 92.8) < 0.01 : "Erreur Test 1 - Net à payer";
        
        // Test 2 vérifications
        assert facture2.calculerTotalHT() == 120.0 : "Erreur Test 2 - Total HT";
        assert facture2.calculerRemise() == 36.0 : "Erreur Test 2 - Remise";
        assert facture2.calculerTotalHTApresRemise() == 84.0 : "Erreur Test 2 - Total HT après remise";
        assert Math.abs(facture2.calculerTVA() - 13.44) < 0.01 : "Erreur Test 2 - TVA";
        assert Math.abs(facture2.calculerNetAPayer() - 97.44) < 0.01 : "Erreur Test 2 - Net à payer";
        
        System.out.println("✓ Tous les tests sont réussis !");
        System.out.println("✓ Les calculs de TVA et remise fonctionnent correctement !");
    }
}
