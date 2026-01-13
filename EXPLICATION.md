# Explication du Code - Pour Débutants

## Introduction
Ce document explique le code de manière simple pour que tu puisses le comprendre et l'expliquer à tes amis.

## Structure du Projet

### 1. Client.java - La classe Client
```java
public class Client {
    private String nom;
    private String telephone;
```

**Explication** : 
- Une classe est comme un "moule" pour créer des objets
- `Client` stocke les infos d'un client : son nom et son téléphone
- `private` signifie que ces variables ne peuvent être modifiées que dans cette classe
- Les méthodes `getNom()`, `setNom()`, etc. permettent de lire et modifier ces valeurs

**Exemple d'utilisation** :
```java
Client client = new Client("Marie", "0123456789");
String nom = client.getNom(); // Récupère "Marie"
```

### 2. Article.java - La classe Article
```java
public class Article {
    private String nom;
    private int quantite;
    private double prixUnitaire;
```

**Explication** :
- Représente un article acheté
- `int` = nombre entier (pour la quantité)
- `double` = nombre décimal (pour les prix)
- La méthode `getTotal()` calcule : quantité × prix unitaire

**Exemple d'utilisation** :
```java
Article pain = new Article("Pain", 2, 15.0);
double total = pain.getTotal(); // 2 × 15.0 = 30.0
```

### 3. Facture.java - La classe Facture (Les Calculs)

**Les constantes** :
```java
private static final double TAUX_TVA = 0.16;      // 16%
private static final double TAUX_REMISE = 0.30;   // 30%
private static final double SEUIL_REMISE = 100.0; // 100$
```
- `static final` = constante qui ne change jamais
- On met les taux en décimal : 16% = 0.16

**ArrayList** :
```java
private ArrayList<Article> articles;
```
- Une liste qui peut grandir/rétrécir automatiquement
- Stocke tous les articles de la facture

**Méthodes de Calcul** :

1. **calculerTotalHT()** - Total Hors Taxe
```java
double total = 0;
for (Article article : articles) {
    total += article.getTotal();
}
return total;
```
- Parcourt tous les articles
- Additionne leurs totaux
- `+=` signifie "ajouter à"

2. **calculerRemise()** - Remise de 30%
```java
if (totalHT >= SEUIL_REMISE) {
    return totalHT * TAUX_REMISE;
}
return 0;
```
- Si le total ≥ 100$, on applique 30% de remise
- Sinon, remise = 0

3. **calculerTVA()** - Taxe sur la Valeur Ajoutée
```java
return calculerTotalHTApresRemise() * TAUX_TVA;
```
- On calcule 16% sur le total après remise

4. **calculerNetAPayer()** - Montant Final
```java
return calculerTotalHTApresRemise() + calculerTVA();
```
- Total après remise + TVA = ce que le client paie

### 4. FactureGUI.java - L'Interface Graphique

**Imports nécessaires** :
```java
import javax.swing.*;  // Pour les composants GUI
import java.awt.*;     // Pour la mise en page
import java.awt.event.*; // Pour gérer les clics
```

**Composants Swing utilisés** :

1. **JFrame** : La fenêtre principale
```java
public class FactureGUI extends JFrame
```

2. **JTextField** : Champs de saisie
```java
private JTextField nomClientField;
```

3. **JButton** : Boutons cliquables
```java
JButton btnSuivant = new JButton("Suivant");
```

4. **JLabel** : Texte d'affichage
```java
new JLabel("Nom du client:")
```

5. **JTextArea** : Zone de texte multi-ligne
```java
JTextArea textArea = new JTextArea();
```

**Gestion des événements** :
```java
btnSuivant.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        validerClient();
    }
});
```
- Quand on clique sur le bouton, la méthode `validerClient()` est appelée

**Validation des données** :
```java
if (nom.isEmpty() || telephone.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs!");
    return;
}
```
- Vérifie que les champs ne sont pas vides
- Affiche un message d'erreur si nécessaire

**Conversion String → Nombre** :
```java
int nbArticles = Integer.parseInt(nbArticlesStr);
double prix = Double.parseDouble(prixStr);
```
- `parseInt()` : convertit texte en nombre entier
- `parseDouble()` : convertit texte en nombre décimal

## Flux de l'Application

1. **Démarrage** → Formulaire client
2. **Validation client** → Formulaires articles (un par un)
3. **Validation articles** → Affichage de la facture
4. **Nouvelle facture** → Retour au début
5. **Quitter** → Fermer l'application

## Concepts Java Utilisés (Simple)

### Encapsulation
- Variables `private`
- Méthodes `public` pour y accéder
- **Pourquoi ?** Protège les données et contrôle l'accès

### Héritage
```java
public class FactureGUI extends JFrame
```
- `FactureGUI` hérite de `JFrame`
- Obtient toutes les fonctionnalités d'une fenêtre

### Constructeur
```java
public Client(String nom, String telephone) {
    this.nom = nom;
    this.telephone = telephone;
}
```
- Méthode spéciale appelée lors de `new Client(...)`
- Initialise l'objet

## Conseils pour Expliquer à tes Amis

1. **Commence par les classes simples** : Client et Article
2. **Montre comment on crée des objets** : `new Client(...)`
3. **Explique les calculs un par un** : Total HT → Remise → TVA → Net
4. **Démo de l'interface** : Lance l'application et montre comment ça marche
5. **Montre le code de validation** : Comment on vérifie les erreurs

## Questions Fréquentes

**Q: Pourquoi utiliser des classes ?**
R: Pour organiser le code et représenter des objets réels (client, article, facture)

**Q: C'est quoi ArrayList ?**
R: Une liste dynamique qui peut contenir plusieurs éléments du même type

**Q: Pourquoi extends JFrame ?**
R: Pour que notre classe ait toutes les fonctionnalités d'une fenêtre Swing

**Q: À quoi sert ActionListener ?**
R: À réagir quand l'utilisateur clique sur un bouton

**Q: Comment fonctionne le calcul de la TVA ?**
R: On multiplie le total après remise par 0.16 (16%)

## Test Manuel Rapide

1. Lance l'application : `java FactureGUI`
2. Entre : "Jean Dupont", "0123", 1 article
3. Article : "Pain", quantité 120, prix 1
4. Vérifie : Total HT = 120, Remise = 36, TVA = 13.44, Net = 97.44
