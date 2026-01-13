# Guide d'Utilisation - Système de Facturation

## Vue d'ensemble
Ce système de facturation est conçu pour être simple et facile à utiliser, parfait pour un Super Marché.

## Démarrage de l'Application

1. Ouvrez un terminal dans le dossier du projet
2. Naviguez vers le dossier `src` : `cd src`
3. Compilez les fichiers : `javac *.java`
4. Lancez l'application : `java FactureGUI`

## Utilisation Étape par Étape

### Étape 1 : Informations du Client
L'application s'ouvre avec un formulaire pour entrer les informations du client :
- **Nom du client** : Entrez le nom complet du client
- **Numéro de téléphone** : Entrez le numéro de téléphone du client
- **Nombre d'articles** : Indiquez combien d'articles le client achète
- Cliquez sur "Suivant" pour continuer

### Étape 2 : Saisie des Articles
Pour chaque article, vous devrez entrer :
- **Nom de l'article** : Le nom du produit
- **Quantité** : Combien d'unités sont achetées
- **Prix unitaire** : Le prix d'une unité en dollars

Le système vous guide article par article. Après avoir entré les informations de chaque article, cliquez sur :
- "Article Suivant" s'il reste des articles à saisir
- "Terminer" pour le dernier article

### Étape 3 : Affichage de la Facture
Une fois tous les articles saisis, la facture complète s'affiche avec :

#### Informations du Client
- Nom et téléphone du client

#### Liste des Articles
- Chaque article avec sa quantité, prix unitaire et total

#### Calculs Financiers
1. **Total Hors Taxe (HT)** : Somme de tous les articles
2. **Remise** : 30% du Total HT si celui-ci est ≥ 100$
3. **Total après remise** : Total HT - Remise
4. **TVA** : 16% du Total après remise
5. **Net à payer** : Montant final que le client doit payer

### Options Finales
- **Nouvelle Facture** : Recommencer avec un nouveau client
- **Quitter** : Fermer l'application

## Exemples de Calculs

### Exemple 1 : Facture sans remise (Total HT < 100$)
```
Articles :
- Pain x 2 @ 15.00$ = 30.00$
- Lait x 3 @ 10.00$ = 30.00$

Total HT : 60.00$
Remise : 0.00$ (car Total HT < 100$)
TVA (16%) : 9.60$
Net à payer : 69.60$
```

### Exemple 2 : Facture avec remise (Total HT ≥ 100$)
```
Articles :
- Ordinateur x 1 @ 120.00$ = 120.00$

Total HT : 120.00$
Remise (30%) : -36.00$ (car Total HT ≥ 100$)
Total après remise : 84.00$
TVA (16%) : 13.44$
Net à payer : 97.44$
```

## Architecture du Code

### Classes Principales

#### Client.java
- Stocke les informations du client (nom, téléphone)
- Méthodes getter/setter simples

#### Article.java
- Représente un article avec nom, quantité, prix unitaire
- Méthode `getTotal()` pour calculer le total de l'article

#### Facture.java
- Gère la logique de calcul de la facture
- Constantes pour TVA (16%) et remise (30%)
- Méthodes de calcul :
  - `calculerTotalHT()` : Total hors taxe
  - `calculerRemise()` : Remise si applicable
  - `calculerTotalHTApresRemise()` : Total après remise
  - `calculerTVA()` : Calcul de la TVA
  - `calculerNetAPayer()` : Montant final

#### FactureGUI.java
- Interface graphique Swing
- Gère les formulaires et l'affichage de la facture
- Point d'entrée du programme (méthode main)

## Remarques Importantes

1. **Validation des données** : L'application vérifie que tous les champs sont remplis et que les valeurs sont valides
2. **Messages d'erreur** : Des messages clairs s'affichent en cas d'erreur de saisie
3. **Format des prix** : Tous les montants sont en dollars ($)
4. **Remise automatique** : La remise de 30% s'applique automatiquement si le Total HT ≥ 100$
