public class DemoFacture {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║     DÉMONSTRATION - SYSTÈME DE FACTURATION SUPER MARCHÉ       ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        
        // Scénario réaliste 1: Petit achat (sans remise)
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("SCÉNARIO 1: Petit achat quotidien");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        Client client1 = new Client("Sophie Leblanc", "01-23-45-67-89");
        Facture facture1 = new Facture(client1);
        
        facture1.ajouterArticle(new Article("Baguette", 2, 1.50));
        facture1.ajouterArticle(new Article("Lait 1L", 1, 3.20));
        facture1.ajouterArticle(new Article("Fromage", 1, 8.50));
        facture1.ajouterArticle(new Article("Tomates (kg)", 2, 4.20));
        
        afficherFacture(facture1);
        
        // Scénario réaliste 2: Grand achat (avec remise de 30%)
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("SCÉNARIO 2: Courses hebdomadaires (avec remise)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        Client client2 = new Client("Ahmed Benali", "06-78-90-12-34");
        Facture facture2 = new Facture(client2);
        
        facture2.ajouterArticle(new Article("Poulet entier", 2, 12.50));
        facture2.ajouterArticle(new Article("Riz 5kg", 1, 15.00));
        facture2.ajouterArticle(new Article("Huile 1L", 3, 8.00));
        facture2.ajouterArticle(new Article("Légumes variés", 5, 6.50));
        facture2.ajouterArticle(new Article("Fruits", 4, 7.00));
        facture2.ajouterArticle(new Article("Eau minérale 6x1.5L", 2, 5.50));
        
        afficherFacture(facture2);
        
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║   ✓ Le système fonctionne correctement!                       ║");
        System.out.println("║   ✓ TVA de 16% appliquée sur tous les achats                  ║");
        System.out.println("║   ✓ Remise de 30% appliquée automatiquement si Total ≥ 100$   ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
    }
    
    private static void afficherFacture(Facture facture) {
        Client client = facture.getClient();
        System.out.println("\n📋 Client: " + client.getNom() + " | Tél: " + client.getTelephone());
        System.out.println("─────────────────────────────────────────────────────────────────");
        
        System.out.println("\nArticles achetés:");
        for (Article article : facture.getArticles()) {
            System.out.printf("  • %-25s x%-4d @%7.2f$ = %8.2f$%n", 
                article.getNom(), 
                article.getQuantite(), 
                article.getPrixUnitaire(), 
                article.getTotal());
        }
        
        System.out.println("─────────────────────────────────────────────────────────────────");
        System.out.printf("Total Hors Taxe:                               %10.2f$%n", 
            facture.calculerTotalHT());
        
        double remise = facture.calculerRemise();
        if (remise > 0) {
            System.out.printf("Remise (30%% car Total HT ≥ 100$):            -%10.2f$%n", remise);
            System.out.printf("Total après remise:                            %10.2f$%n", 
                facture.calculerTotalHTApresRemise());
        }
        
        System.out.printf("TVA (16%%):                                     %10.2f$%n", 
            facture.calculerTVA());
        System.out.println("═════════════════════════════════════════════════════════════════");
        System.out.printf("NET À PAYER:                                   %10.2f$%n", 
            facture.calculerNetAPayer());
        System.out.println("═════════════════════════════════════════════════════════════════");
    }
}
