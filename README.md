# Système de Facturation - Super Marché

Programme Java avec interface graphique (Swing) pour gérer la facturation d'un super marché.

## Fonctionnalités

Le programme permet de :
- Enregistrer les informations du client (nom, téléphone)
- Saisir plusieurs articles avec leur nom, quantité et prix unitaire
- Calculer automatiquement :
  - Le Total Hors Taxe
  - La TVA (16%)
  - La remise de 30% (si Total HT ≥ 100$)
  - Le Net à payer
- Afficher une facture complète avec tous les détails

## Structure du Projet

Le projet utilise la programmation orientée objet avec 3 classes principales :

- **Article.java** : Représente un article avec son nom, quantité et prix unitaire
- **Client.java** : Représente un client avec son nom et téléphone
- **Facture.java** : Gère les calculs (Total HT, TVA, remise, Net à payer)
- **FactureGUI.java** : Interface graphique Swing (fenêtre principale)

## Comment Compiler et Exécuter

### Compilation

```bash
cd src
javac *.java
```

### Exécution

```bash
java FactureGUI
```

## Utilisation

1. **Écran 1** : Entrer les informations du client
   - Nom du client
   - Numéro de téléphone
   - Nombre d'articles à acheter

2. **Écran 2** : Entrer les détails de chaque article
   - Nom de l'article
   - Quantité
   - Prix unitaire (en $)

3. **Écran 3** : Affichage de la facture complète
   - Informations du client
   - Liste des articles achetés
   - Calculs (Total HT, Remise, TVA, Net à payer)

## Exemple de Calcul

Si un client achète pour 120$ HT :
- Total HT : 120.00 $
- Remise 30% : -36.00 $ (car ≥ 100$)
- Total HT après remise : 84.00 $
- TVA 16% : 13.44 $
- **Net à payer : 97.44 $**

Si un client achète pour 80$ HT :
- Total HT : 80.00 $
- Remise : 0.00 $ (car < 100$)
- Total HT après remise : 80.00 $
- TVA 16% : 12.80 $
- **Net à payer : 92.80 $**
