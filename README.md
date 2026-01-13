# java-facture-gui
Travail gui de Java - Système de Gestion de Facturation pour Super Marché

## Description
Ce programme permet à un Super Marché de gérer sa facturation avec une interface graphique Java Swing.

## Fonctionnalités
- Enregistrement des informations du client (nom, téléphone)
- Saisie de plusieurs articles avec leur nom, quantité et prix unitaire
- Calcul automatique de la TVA (16%)
- Application d'une remise de 30% sur le Total Hors Taxe si celui-ci est ≥ 100$
- Affichage complet de la facture avec tous les détails

## Structure du Projet
- **Client.java** : Classe pour gérer les informations du client
- **Article.java** : Classe pour représenter un article acheté
- **Facture.java** : Classe pour gérer les calculs (TVA, remise, totaux)
- **FactureGUI.java** : Interface graphique Swing (point d'entrée du programme)

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
1. Entrez le nom du client et son numéro de téléphone
2. Indiquez le nombre d'articles à acheter
3. Pour chaque article, saisissez son nom, la quantité et le prix unitaire
4. La facture complète s'affiche avec tous les calculs
5. Vous pouvez créer une nouvelle facture ou quitter l'application

## Exemple de Calcul
- Total HT: 120.00 $
- Remise (30%): -36.00 $ (car Total HT ≥ 100$)
- Total après remise: 84.00 $
- TVA (16%): 13.44 $
- **Net à payer: 97.44 $**
