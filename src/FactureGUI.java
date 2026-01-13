import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FactureGUI extends JFrame {
    private JTextField nomClientField;
    private JTextField telephoneField;
    private JTextField nombreArticlesField;
    private JButton suivantButton;
    private Facture facture;
    
    public FactureGUI() {
        setTitle("Système de Facturation - Super Marché");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        afficherFormulaireClient();
    }
    
    private void afficherFormulaireClient() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(new JLabel("Nom du Client:"));
        nomClientField = new JTextField();
        panel.add(nomClientField);
        
        panel.add(new JLabel("Numéro de Téléphone:"));
        telephoneField = new JTextField();
        panel.add(telephoneField);
        
        panel.add(new JLabel("Nombre d'Articles:"));
        nombreArticlesField = new JTextField();
        panel.add(nombreArticlesField);
        
        suivantButton = new JButton("Suivant");
        suivantButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                validerClient();
            }
        });
        panel.add(new JLabel(""));
        panel.add(suivantButton);
        
        setContentPane(panel);
        setVisible(true);
    }
    
    private void validerClient() {
        String nom = nomClientField.getText();
        String telephone = telephoneField.getText();
        String nombreStr = nombreArticlesField.getText();
        
        if (nom.isEmpty() || telephone.isEmpty() || nombreStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs!", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int nombreArticles = Integer.parseInt(nombreStr);
            if (nombreArticles <= 0) {
                JOptionPane.showMessageDialog(this, "Le nombre d'articles doit être positif!", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Client client = new Client(nom, telephone);
            facture = new Facture(client);
            afficherFormulaireArticles(nombreArticles);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Le nombre d'articles doit être un nombre!", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void afficherFormulaireArticles(int nombreArticles) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(nombreArticles + 1, 4, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        formPanel.add(new JLabel("Nom Article"));
        formPanel.add(new JLabel("Quantité"));
        formPanel.add(new JLabel("Prix Unitaire ($)"));
        formPanel.add(new JLabel(""));
        
        JTextField[] nomsFields = new JTextField[nombreArticles];
        JTextField[] quantitesFields = new JTextField[nombreArticles];
        JTextField[] prixFields = new JTextField[nombreArticles];
        
        for (int i = 0; i < nombreArticles; i++) {
            nomsFields[i] = new JTextField();
            quantitesFields[i] = new JTextField();
            prixFields[i] = new JTextField();
            
            formPanel.add(nomsFields[i]);
            formPanel.add(quantitesFields[i]);
            formPanel.add(prixFields[i]);
            formPanel.add(new JLabel(""));
        }
        
        JButton calculerButton = new JButton("Calculer la Facture");
        calculerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                validerArticles(nomsFields, quantitesFields, prixFields);
            }
        });
        
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(calculerButton, BorderLayout.SOUTH);
        
        setContentPane(panel);
        revalidate();
    }
    
    private void validerArticles(JTextField[] nomsFields, JTextField[] quantitesFields, JTextField[] prixFields) {
        try {
            for (int i = 0; i < nomsFields.length; i++) {
                String nom = nomsFields[i].getText();
                String quantiteStr = quantitesFields[i].getText();
                String prixStr = prixFields[i].getText();
                
                if (nom.isEmpty() || quantiteStr.isEmpty() || prixStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs pour l'article " + (i + 1) + "!", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                int quantite = Integer.parseInt(quantiteStr);
                double prix = Double.parseDouble(prixStr);
                
                if (quantite <= 0 || prix <= 0) {
                    JOptionPane.showMessageDialog(this, "La quantité et le prix doivent être positifs!", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Article article = new Article(nom, quantite, prix);
                facture.ajouterArticle(article);
            }
            
            afficherFacture();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs numériques valides!", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void afficherFacture() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        StringBuilder sb = new StringBuilder();
        sb.append("==============================================\n");
        sb.append("          FACTURE - SUPER MARCHÉ\n");
        sb.append("==============================================\n\n");
        
        sb.append("INFORMATIONS CLIENT:\n");
        sb.append("Nom: ").append(facture.getClient().getNom()).append("\n");
        sb.append("Téléphone: ").append(facture.getClient().getTelephone()).append("\n\n");
        
        sb.append("==============================================\n");
        sb.append("ARTICLES ACHETÉS:\n");
        sb.append("==============================================\n");
        sb.append(String.format("%-20s %8s %12s %12s\n", "Article", "Qté", "Prix Unit.", "Total"));
        sb.append("----------------------------------------------\n");
        
        for (Article article : facture.getArticles()) {
            sb.append(String.format("%-20s %8d %12.2f $ %12.2f $\n", 
                article.getNom(), 
                article.getQuantite(), 
                article.getPrixUnitaire(), 
                article.getPrixTotal()));
        }
        
        sb.append("==============================================\n\n");
        
        double totalHT = facture.calculerTotalHT();
        double remise = facture.calculerRemise();
        double totalHTApresRemise = facture.calculerTotalHTApresRemise();
        double tva = facture.calculerTVA();
        double netAPayer = facture.calculerNetAPayer();
        
        sb.append("RÉSUMÉ:\n");
        sb.append("----------------------------------------------\n");
        sb.append(String.format("Total Hors Taxe:         %15.2f $\n", totalHT));
        sb.append(String.format("Remise (30%%):            %15.2f $\n", remise));
        sb.append(String.format("Total HT après remise:   %15.2f $\n", totalHTApresRemise));
        sb.append(String.format("TVA (16%%):               %15.2f $\n", tva));
        sb.append("==============================================\n");
        sb.append(String.format("NET À PAYER:             %15.2f $\n", netAPayer));
        sb.append("==============================================\n");
        
        textArea.setText(sb.toString());
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        JButton nouvelleButton = new JButton("Nouvelle Facture");
        nouvelleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new FactureGUI();
            }
        });
        panel.add(nouvelleButton, BorderLayout.SOUTH);
        
        setContentPane(panel);
        revalidate();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FactureGUI();
            }
        });
    }
}
