// Classe pour formater l'affichage des factures
public class FormatFacture {
    
    // Formater une facture complète
    public static String formaterFacture(Facture facture) {
        StringBuilder sb = new StringBuilder();
        
        ajouterEntete(sb);
        ajouterInfoClient(sb, facture.getClient());
        ajouterListeArticles(sb, facture);
        ajouterCalculs(sb, facture);
        
        return sb.toString();
    }
    
    // Ajouter l'en-tête de la facture
    private static void ajouterEntete(StringBuilder sb) {
        sb.append("========================================\n");
        sb.append("       FACTURE - SUPER MARCHÉ\n");
        sb.append("========================================\n\n");
    }
    
    // Ajouter les informations du client
    private static void ajouterInfoClient(StringBuilder sb, Client client) {
        sb.append("INFORMATIONS CLIENT:\n");
        sb.append("Nom: ").append(client.getNom()).append("\n");
        sb.append("Téléphone: ").append(client.getTelephone()).append("\n\n");
    }
    
    // Ajouter la liste des articles
    private static void ajouterListeArticles(StringBuilder sb, Facture facture) {
        sb.append("ARTICLES ACHETÉS:\n");
        sb.append("----------------------------------------\n");
        
        int i = 1;
        for (Article article : facture.getArticles()) {
            sb.append(i++).append(". ").append(article.getNom()).append("\n");
            sb.append("   Quantité: ").append(article.getQuantite()).append("\n");
            sb.append("   Prix unitaire: ").append(String.format("%.2f", article.getPrixUnitaire())).append(" $\n");
            sb.append("   Total: ").append(String.format("%.2f", article.getTotal())).append(" $\n\n");
        }
    }
    
    // Ajouter les calculs finaux
    private static void ajouterCalculs(StringBuilder sb, Facture facture) {
        sb.append("========================================\n");
        sb.append("CALCULS:\n");
        sb.append("----------------------------------------\n");
        sb.append("Total Hors Taxe: ").append(String.format("%.2f", facture.calculerTotalHT())).append(" $\n");
        sb.append("Remise (30%): ").append(String.format("%.2f", facture.calculerRemise())).append(" $\n");
        sb.append("TVA (16%): ").append(String.format("%.2f", facture.calculerTVA())).append(" $\n");
        sb.append("========================================\n");
        sb.append("NET À PAYER: ").append(String.format("%.2f", facture.calculerNetAPayer())).append(" $\n");
        sb.append("========================================\n");
    }
}
