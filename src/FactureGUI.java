import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Interface graphique pour la facturation
public class FactureGUI extends JFrame {
    
    // Composants pour les informations du client
    private JTextField txtNomClient;
    private JTextField txtTelephone;
    
    // Composants pour les articles
    private JTextField txtNomArticle;
    private JTextField txtQuantite;
    private JTextField txtPrixUnitaire;
    private JButton btnAjouterArticle;
    
    // Boutons
    private JButton btnAfficherFacture;
    private JButton btnNouvelleFacture;
    
    // Zone d'affichage
    private JTextArea txtResultat;
    
    // La facture en cours
    private Facture facture;
    
    // Constructeur
    public FactureGUI() {
        // Configuration de la fenêtre
        setTitle("Gestion de Facturation - Super Marché");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Créer les panels
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel Client
        JPanel panelClient = creerPanelClient();
        panelPrincipal.add(panelClient);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Panel Articles
        JPanel panelArticles = creerPanelArticles();
        panelPrincipal.add(panelArticles);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Panel Boutons
        JPanel panelBoutons = creerPanelBoutons();
        panelPrincipal.add(panelBoutons);
        
        // Zone de résultat
        txtResultat = new JTextArea(15, 50);
        txtResultat.setEditable(false);
        txtResultat.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtResultat);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Facture"));
        
        // Ajouter les composants à la fenêtre
        add(panelPrincipal, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // Initialiser
        nouvelleFacture();
    }
    
    // Méthode utilitaire pour ajouter un champ de saisie
    private void ajouterChampSaisie(JPanel panel, String label, JTextField champ) {
        panel.add(new JLabel(label));
        panel.add(champ);
    }
    
    // Créer le panel pour les informations du client
    private JPanel creerPanelClient() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Informations Client"));
        
        txtNomClient = new JTextField(20);
        ajouterChampSaisie(panel, "Nom du client:", txtNomClient);
        
        txtTelephone = new JTextField(20);
        ajouterChampSaisie(panel, "Téléphone:", txtTelephone);
        
        return panel;
    }
    
    // Créer le panel pour les articles
    private JPanel creerPanelArticles() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Articles"));
        
        txtNomArticle = new JTextField(20);
        ajouterChampSaisie(panel, "Nom de l'article:", txtNomArticle);
        
        txtQuantite = new JTextField(20);
        ajouterChampSaisie(panel, "Quantité:", txtQuantite);
        
        txtPrixUnitaire = new JTextField(20);
        ajouterChampSaisie(panel, "Prix unitaire ($):", txtPrixUnitaire);
        
        panel.add(new JLabel(""));
        btnAjouterArticle = new JButton("Ajouter Article");
        btnAjouterArticle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ajouterArticle();
            }
        });
        panel.add(btnAjouterArticle);
        
        return panel;
    }
    
    // Créer le panel pour les boutons
    private JPanel creerPanelBoutons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        
        btnAfficherFacture = new JButton("Afficher Facture");
        btnAfficherFacture.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                afficherFacture();
            }
        });
        
        btnNouvelleFacture = new JButton("Nouvelle Facture");
        btnNouvelleFacture.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nouvelleFacture();
            }
        });
        
        panel.add(btnAfficherFacture);
        panel.add(btnNouvelleFacture);
        
        return panel;
    }
    
    // Ajouter un article
    private void ajouterArticle() {
        try {
            String nom = txtNomArticle.getText().trim();
            int quantite = Integer.parseInt(txtQuantite.getText().trim());
            double prix = Double.parseDouble(txtPrixUnitaire.getText().trim());
            
            if (nom.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer le nom de l'article!", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (quantite <= 0) {
                JOptionPane.showMessageDialog(this, "La quantité doit être positive!", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (prix <= 0) {
                JOptionPane.showMessageDialog(this, "Le prix doit être positif!", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Article article = new Article(nom, quantite, prix);
            facture.ajouterArticle(article);
            
            // Effacer les champs
            txtNomArticle.setText("");
            txtQuantite.setText("");
            txtPrixUnitaire.setText("");
            
            JOptionPane.showMessageDialog(this, "Article ajouté avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs valides!", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Afficher la facture
    private void afficherFacture() {
        String nomClient = txtNomClient.getText().trim();
        String telephone = txtTelephone.getText().trim();
        
        if (nomClient.isEmpty() || telephone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir les informations du client!", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (facture.getArticles().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez ajouter au moins un article!", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Créer le client et mettre à jour la facture
        Client client = new Client(nomClient, telephone);
        ArrayList<Article> articlesTemp = new ArrayList<Article>(facture.getArticles());
        facture = new Facture(client);
        
        // Ajouter les articles
        for (Article art : articlesTemp) {
            facture.ajouterArticle(art);
        }
        
        // Utiliser FormatFacture pour générer le texte
        String texteFacture = FormatFacture.formaterFacture(facture);
        txtResultat.setText(texteFacture);
    }
    
    // Nouvelle facture
    private void nouvelleFacture() {
        facture = new Facture(null);
        txtNomClient.setText("");
        txtTelephone.setText("");
        txtNomArticle.setText("");
        txtQuantite.setText("");
        txtPrixUnitaire.setText("");
        txtResultat.setText("");
    }
    
    // Main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FactureGUI gui = new FactureGUI();
                gui.setVisible(true);
            }
        });
    }
}
