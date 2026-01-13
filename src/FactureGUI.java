import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FactureGUI extends JFrame {
    private JTextField nomClientField;
    private JTextField telephoneField;
    private JTextField nomArticleField;
    private JTextField quantiteField;
    private JTextField prixUnitaireField;
    private JTextArea affichageArea;
    private JButton ajouterArticleButton;
    private JButton calculerButton;
    private JButton nouvelleFactureButton;
    
    private Facture factureActuelle;
    
    public FactureGUI() {
        setTitle("Système de Facturation - Super Marché");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel panelClient = creerPanelClient();
        JPanel panelArticle = creerPanelArticle();
        JPanel panelBoutons = creerPanelBoutons();
        
        panelPrincipal.add(panelClient);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        panelPrincipal.add(panelArticle);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        panelPrincipal.add(panelBoutons);
        
        affichageArea = new JTextArea(20, 60);
        affichageArea.setEditable(false);
        affichageArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(affichageArea);
        
        add(panelPrincipal, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        factureActuelle = null;
    }
    
    private JPanel creerPanelClient() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Informations Client"));
        panel.setLayout(new GridLayout(2, 2, 5, 5));
        
        panel.add(new JLabel("Nom du client:"));
        nomClientField = new JTextField(20);
        panel.add(nomClientField);
        
        panel.add(new JLabel("Numéro de téléphone:"));
        telephoneField = new JTextField(20);
        panel.add(telephoneField);
        
        return panel;
    }
    
    private JPanel creerPanelArticle() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Ajouter un Article"));
        panel.setLayout(new GridLayout(3, 2, 5, 5));
        
        panel.add(new JLabel("Nom de l'article:"));
        nomArticleField = new JTextField(20);
        panel.add(nomArticleField);
        
        panel.add(new JLabel("Quantité:"));
        quantiteField = new JTextField(20);
        panel.add(quantiteField);
        
        panel.add(new JLabel("Prix unitaire ($):"));
        prixUnitaireField = new JTextField(20);
        panel.add(prixUnitaireField);
        
        return panel;
    }
    
    private JPanel creerPanelBoutons() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        
        ajouterArticleButton = new JButton("Ajouter Article");
        ajouterArticleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ajouterArticle();
            }
        });
        
        calculerButton = new JButton("Calculer Facture");
        calculerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculerFacture();
            }
        });
        
        nouvelleFactureButton = new JButton("Nouvelle Facture");
        nouvelleFactureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nouvelleFacture();
            }
        });
        
        panel.add(ajouterArticleButton);
        panel.add(calculerButton);
        panel.add(nouvelleFactureButton);
        
        return panel;
    }
    
    private void ajouterArticle() {
        try {
            if (factureActuelle == null) {
                String nomClient = nomClientField.getText().trim();
                String telephone = telephoneField.getText().trim();
                
                if (nomClient.isEmpty() || telephone.isEmpty()) {
                    JOptionPane.showMessageDialog(this, 
                        "Veuillez remplir les informations du client d'abord!", 
                        "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Client client = new Client(nomClient, telephone);
                factureActuelle = new Facture(client);
            }
            
            String nomArticle = nomArticleField.getText().trim();
            String quantiteStr = quantiteField.getText().trim();
            String prixStr = prixUnitaireField.getText().trim();
            
            if (nomArticle.isEmpty() || quantiteStr.isEmpty() || prixStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Veuillez remplir tous les champs de l'article!", 
                    "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int quantite = Integer.parseInt(quantiteStr);
            double prixUnitaire = Double.parseDouble(prixStr);
            
            if (quantite <= 0 || prixUnitaire <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "La quantité et le prix doivent être positifs!", 
                    "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Article article = new Article(nomArticle, quantite, prixUnitaire);
            factureActuelle.ajouterArticle(article);
            
            nomArticleField.setText("");
            quantiteField.setText("");
            prixUnitaireField.setText("");
            
            affichageArea.append("Article ajouté: " + nomArticle + " x" + quantite + " @ $" + prixUnitaire + "\n");
            
            JOptionPane.showMessageDialog(this, 
                "Article ajouté avec succès!", 
                "Succès", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez entrer des valeurs numériques valides!", 
                "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void calculerFacture() {
        if (factureActuelle == null || factureActuelle.getArticles().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez ajouter au moins un article!", 
                "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        affichageArea.setText("");
        affichageArea.append("===========================================\n");
        affichageArea.append("          FACTURE - SUPER MARCHÉ\n");
        affichageArea.append("===========================================\n\n");
        
        Client client = factureActuelle.getClient();
        affichageArea.append("Client: " + client.getNom() + "\n");
        affichageArea.append("Téléphone: " + client.getNumeroTelephone() + "\n\n");
        
        affichageArea.append("-------------------------------------------\n");
        affichageArea.append("ARTICLES ACHETÉS:\n");
        affichageArea.append("-------------------------------------------\n");
        
        for (Article article : factureActuelle.getArticles()) {
            affichageArea.append(String.format("%-20s x%-5d $%-8.2f = $%.2f\n", 
                article.getNom(), 
                article.getQuantite(), 
                article.getPrixUnitaire(), 
                article.getPrixTotal()));
        }
        
        affichageArea.append("\n-------------------------------------------\n");
        affichageArea.append("RÉSUMÉ:\n");
        affichageArea.append("-------------------------------------------\n");
        
        double totalHT = factureActuelle.calculerTotalHT();
        double remise = factureActuelle.calculerRemise();
        double tva = factureActuelle.calculerTVA();
        double netAPayer = factureActuelle.calculerNetAPayer();
        
        affichageArea.append(String.format("Total Hors Taxe:        $%.2f\n", totalHT));
        affichageArea.append(String.format("Remise (30%%):           -$%.2f\n", remise));
        affichageArea.append(String.format("TVA (16%%):              $%.2f\n", tva));
        affichageArea.append("-------------------------------------------\n");
        affichageArea.append(String.format("NET À PAYER:            $%.2f\n", netAPayer));
        affichageArea.append("===========================================\n");
    }
    
    private void nouvelleFacture() {
        factureActuelle = null;
        nomClientField.setText("");
        telephoneField.setText("");
        nomArticleField.setText("");
        quantiteField.setText("");
        prixUnitaireField.setText("");
        affichageArea.setText("");
        
        JOptionPane.showMessageDialog(this, 
            "Prêt pour une nouvelle facture!", 
            "Nouvelle Facture", JOptionPane.INFORMATION_MESSAGE);
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
