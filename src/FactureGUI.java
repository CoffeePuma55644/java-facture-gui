import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FactureGUI extends JFrame {
    private JTextField nomClientField;
    private JTextField telephoneField;
    private JTextField nombreArticlesField;
    private JButton btnSuivant;
    private JPanel mainPanel;
    
    private ArrayList<Article> articlesListe;
    private Client client;
    
    public FactureGUI() {
        setTitle("Gestion de Facturation - Super Marché");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        articlesListe = new ArrayList<Article>();
        
        afficherFormulairClient();
    }
    
    private void afficherFormulairClient() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titre = new JLabel("Informations du Client", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 18));
        
        mainPanel.add(new JLabel("Nom du client:"));
        nomClientField = new JTextField();
        mainPanel.add(nomClientField);
        
        mainPanel.add(new JLabel("Numéro de téléphone:"));
        telephoneField = new JTextField();
        mainPanel.add(telephoneField);
        
        mainPanel.add(new JLabel("Nombre d'articles:"));
        nombreArticlesField = new JTextField();
        mainPanel.add(nombreArticlesField);
        
        btnSuivant = new JButton("Suivant");
        btnSuivant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                validerClient();
            }
        });
        
        mainPanel.add(new JLabel(""));
        mainPanel.add(btnSuivant);
        
        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(titre, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    private void validerClient() {
        String nom = nomClientField.getText().trim();
        String telephone = telephoneField.getText().trim();
        String nbArticlesStr = nombreArticlesField.getText().trim();
        
        if (nom.isEmpty() || telephone.isEmpty() || nbArticlesStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs!", 
                "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int nbArticles = Integer.parseInt(nbArticlesStr);
            if (nbArticles <= 0) {
                JOptionPane.showMessageDialog(this, "Le nombre d'articles doit être supérieur à 0!", 
                    "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            client = new Client(nom, telephone);
            afficherFormulairArticles(nbArticles, 0);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre valide pour les articles!", 
                "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void afficherFormulairArticles(final int nbTotal, final int index) {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titre = new JLabel("Article " + (index + 1) + " sur " + nbTotal, SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 18));
        
        mainPanel.add(new JLabel("Nom de l'article:"));
        final JTextField nomArticleField = new JTextField();
        mainPanel.add(nomArticleField);
        
        mainPanel.add(new JLabel("Quantité:"));
        final JTextField quantiteField = new JTextField();
        mainPanel.add(quantiteField);
        
        mainPanel.add(new JLabel("Prix unitaire ($):"));
        final JTextField prixField = new JTextField();
        mainPanel.add(prixField);
        
        JButton btnAjouter = new JButton(index < nbTotal - 1 ? "Article Suivant" : "Terminer");
        btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomArticle = nomArticleField.getText().trim();
                String quantiteStr = quantiteField.getText().trim();
                String prixStr = prixField.getText().trim();
                
                if (nomArticle.isEmpty() || quantiteStr.isEmpty() || prixStr.isEmpty()) {
                    JOptionPane.showMessageDialog(FactureGUI.this, 
                        "Veuillez remplir tous les champs!", 
                        "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                try {
                    int quantite = Integer.parseInt(quantiteStr);
                    double prix = Double.parseDouble(prixStr);
                    
                    if (quantite <= 0 || prix <= 0) {
                        JOptionPane.showMessageDialog(FactureGUI.this, 
                            "La quantité et le prix doivent être supérieurs à 0!", 
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    Article article = new Article(nomArticle, quantite, prix);
                    articlesListe.add(article);
                    
                    if (index < nbTotal - 1) {
                        afficherFormulairArticles(nbTotal, index + 1);
                    } else {
                        afficherFacture();
                    }
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FactureGUI.this, 
                        "Veuillez entrer des valeurs numériques valides!", 
                        "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        mainPanel.add(new JLabel(""));
        mainPanel.add(btnAjouter);
        
        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(titre, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    private void afficherFacture() {
        Facture facture = new Facture(client);
        for (Article article : articlesListe) {
            facture.ajouterArticle(article);
        }
        
        JPanel facturePanel = new JPanel();
        facturePanel.setLayout(new BorderLayout());
        facturePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("        FACTURE - SUPER MARCHÉ\n");
        sb.append("========================================\n\n");
        
        sb.append("INFORMATIONS CLIENT:\n");
        sb.append("  Nom: ").append(client.getNom()).append("\n");
        sb.append("  Téléphone: ").append(client.getTelephone()).append("\n\n");
        
        sb.append("ARTICLES ACHETÉS:\n");
        sb.append("----------------------------------------\n");
        for (Article article : facture.getArticles()) {
            sb.append(String.format("  %-20s", article.getNom()));
            sb.append(String.format(" x%-5d", article.getQuantite()));
            sb.append(String.format(" @%.2f$", article.getPrixUnitaire()));
            sb.append(String.format(" = %.2f$\n", article.getTotal()));
        }
        sb.append("----------------------------------------\n\n");
        
        sb.append("CALCULS:\n");
        sb.append(String.format("  Total Hors Taxe:        %.2f $\n", facture.calculerTotalHT()));
        
        double remise = facture.calculerRemise();
        if (remise > 0) {
            sb.append(String.format("  Remise (30%%):          -%.2f $\n", remise));
            sb.append(String.format("  Total après remise:     %.2f $\n", facture.calculerTotalHTApresRemise()));
        }
        
        sb.append(String.format("  TVA (16%%):             %.2f $\n", facture.calculerTVA()));
        sb.append("========================================\n");
        sb.append(String.format("  NET À PAYER:            %.2f $\n", facture.calculerNetAPayer()));
        sb.append("========================================\n");
        
        textArea.setText(sb.toString());
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        facturePanel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel btnPanel = new JPanel();
        JButton btnNouvelle = new JButton("Nouvelle Facture");
        btnNouvelle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                articlesListe.clear();
                client = null;
                afficherFormulairClient();
            }
        });
        
        JButton btnQuitter = new JButton("Quitter");
        btnQuitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        btnPanel.add(btnNouvelle);
        btnPanel.add(btnQuitter);
        facturePanel.add(btnPanel, BorderLayout.SOUTH);
        
        getContentPane().removeAll();
        getContentPane().add(facturePanel);
        revalidate();
        repaint();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FactureGUI gui = new FactureGUI();
                gui.setVisible(true);
            }
        });
    }
}
