public class TestFacture {
    public static void main(String[] args) {
        System.out.println("=== Test du Système de Facturation ===\n");
        
        // Test 1: Facture sans remise (total < 100$)
        System.out.println("Test 1: Facture sans remise");
        Client client1 = new Client("Jean Dupont", "0612345678");
        Facture facture1 = new Facture(client1);
        
        facture1.ajouterArticle(new Article("Pain", 2, 5.0));
        facture1.ajouterArticle(new Article("Lait", 3, 8.0));
        
        System.out.println("Client: " + client1.getNom());
        System.out.println("Téléphone: " + client1.getNumeroTelephone());
        System.out.println("Nombre d'articles: " + facture1.getArticles().size());
        System.out.println("Total HT: $" + facture1.calculerTotalHT());
        System.out.println("Remise: $" + facture1.calculerRemise());
        System.out.println("TVA (16%): $" + facture1.calculerTVA());
        System.out.println("Net à payer: $" + facture1.calculerNetAPayer());
        
        // Test 2: Facture avec remise (total >= 100$)
        System.out.println("\n\nTest 2: Facture avec remise");
        Client client2 = new Client("Marie Martin", "0698765432");
        Facture facture2 = new Facture(client2);
        
        facture2.ajouterArticle(new Article("Ordinateur", 1, 150.0));
        facture2.ajouterArticle(new Article("Souris", 2, 25.0));
        
        System.out.println("Client: " + client2.getNom());
        System.out.println("Téléphone: " + client2.getNumeroTelephone());
        System.out.println("Nombre d'articles: " + facture2.getArticles().size());
        System.out.println("Total HT: $" + facture2.calculerTotalHT());
        System.out.println("Remise (30%): $" + facture2.calculerRemise());
        System.out.println("TVA (16%): $" + facture2.calculerTVA());
        System.out.println("Net à payer: $" + facture2.calculerNetAPayer());
        
        // Vérifications
        System.out.println("\n\n=== Vérifications ===");
        
        // Facture 1 (sans remise)
        double expectedTotal1 = 34.0;  // (2*5) + (3*8) = 34
        double expectedRemise1 = 0.0;   // Pas de remise car < 100
        double expectedTVA1 = 34.0 * 0.16;  // 5.44
        double expectedNet1 = 34.0 + 5.44;  // 39.44
        
        System.out.println("Test 1 - Total HT correct: " + (facture1.calculerTotalHT() == expectedTotal1));
        System.out.println("Test 1 - Remise correcte: " + (facture1.calculerRemise() == expectedRemise1));
        System.out.println("Test 1 - TVA correcte: " + (Math.abs(facture1.calculerTVA() - expectedTVA1) < 0.01));
        System.out.println("Test 1 - Net correct: " + (Math.abs(facture1.calculerNetAPayer() - expectedNet1) < 0.01));
        
        // Facture 2 (avec remise)
        double expectedTotal2 = 200.0;  // (1*150) + (2*25) = 200
        double expectedRemise2 = 60.0;  // 30% de 200 = 60
        double expectedTVA2 = (200.0 - 60.0) * 0.16;  // 140 * 0.16 = 22.4
        double expectedNet2 = 200.0 - 60.0 + 22.4;  // 162.4
        
        System.out.println("Test 2 - Total HT correct: " + (facture2.calculerTotalHT() == expectedTotal2));
        System.out.println("Test 2 - Remise correcte: " + (facture2.calculerRemise() == expectedRemise2));
        System.out.println("Test 2 - TVA correcte: " + (Math.abs(facture2.calculerTVA() - expectedTVA2) < 0.01));
        System.out.println("Test 2 - Net correct: " + (Math.abs(facture2.calculerNetAPayer() - expectedNet2) < 0.01));
    }
}
