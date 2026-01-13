# java-facture-gui
Travail gui de Java - Système de Gestion de Facturation pour Super Marché

## Description
Ce programme permet à un super marché de gérer sa facturation avec une interface graphique (GUI) développée en Java Swing.

## Fonctionnalités
- Enregistrement des informations client (nom et téléphone)
- Ajout d'articles avec nom, quantité et prix unitaire
- Calcul automatique de la TVA (16%)
- Application automatique d'une remise de 30% si le Total Hors Taxe ≥ 100$
- Affichage complet de la facture avec tous les détails

## Comment compiler et exécuter

### Compilation
```bash
cd src
javac *.java
```

### Exécution
```bash
cd src
java FactureGUI
```

## Utilisation
1. Remplir les informations du client (nom et téléphone)
2. Ajouter des articles un par un en remplissant:
   - Nom de l'article
   - Quantité
   - Prix unitaire
3. Cliquer sur "Ajouter Article" pour chaque article
4. Cliquer sur "Afficher Facture" pour voir le résumé complet
5. Utiliser "Nouvelle Facture" pour recommencer

## Structure du projet
- `Article.java` - Classe représentant un article
- `Client.java` - Classe représentant un client
- `Facture.java` - Classe gérant les calculs de facturation
- `FactureGUI.java` - Interface graphique principale

