# Guide Visuel de l'Application

## Écran 1 : Formulaire Client

L'application démarre avec une fenêtre de 600x500 pixels intitulée "Système de Facturation - Super Marché".

**Champs affichés :**
- Nom du Client : [Champ de texte]
- Numéro de Téléphone : [Champ de texte]
- Nombre d'Articles : [Champ de texte]
- Bouton "Suivant"

**Validation :**
- Tous les champs sont obligatoires
- Le nombre d'articles doit être un nombre positif

---

## Écran 2 : Formulaire Articles

Une fois les informations client validées, l'application affiche un tableau pour saisir les articles.

**Structure du tableau :**
```
| Nom Article | Quantité | Prix Unitaire ($) |
|-------------|----------|-------------------|
| [Texte]     | [Nombre] | [Nombre]          |
| [Texte]     | [Nombre] | [Nombre]          |
| ...         | ...      | ...               |
```

**Bouton "Calculer la Facture"** en bas de l'écran

**Validation :**
- Tous les champs doivent être remplis
- Quantité et prix doivent être des nombres positifs

---

## Écran 3 : Facture Finale

La facture s'affiche dans une zone de texte non modifiable avec défilement.

**Format de la facture :**

```
==============================================
          FACTURE - SUPER MARCHÉ
==============================================

INFORMATIONS CLIENT:
Nom: [Nom du client]
Téléphone: [Numéro]

==============================================
ARTICLES ACHETÉS:
==============================================
Article              Qté    Prix Unit.        Total
----------------------------------------------
[Article 1]            2        10.00 $       20.00 $
[Article 2]            3        20.00 $       60.00 $
==============================================

RÉSUMÉ:
----------------------------------------------
Total Hors Taxe:                      80.00 $
Remise (30%):                          0.00 $
Total HT après remise:                80.00 $
TVA (16%):                            12.80 $
==============================================
NET À PAYER:                          92.80 $
==============================================
```

**Bouton "Nouvelle Facture"** en bas permet de recommencer

---

## Règles de Calcul Implémentées

### 1. Total Hors Taxe (HT)
Somme de tous les (quantité × prix unitaire)

### 2. Remise
- SI Total HT >= 100$ ALORS Remise = Total HT × 30%
- SINON Remise = 0$

### 3. Total HT après remise
Total HT - Remise

### 4. TVA
Total HT après remise × 16%

### 5. Net à Payer
Total HT après remise + TVA

---

## Exemples Concrets

### Exemple 1 : Sans remise (Total HT = 80$)
- 2 Pains à 10$ = 20$
- 3 Laits à 20$ = 60$
- **Total HT : 80.00$**
- **Remise : 0.00$** (< 100$)
- **Total HT après remise : 80.00$**
- **TVA (16%) : 12.80$**
- **NET À PAYER : 92.80$**

### Exemple 2 : Avec remise (Total HT = 120$)
- 1 Ordinateur à 100$ = 100$
- 2 Souris à 10$ = 20$
- **Total HT : 120.00$**
- **Remise (30%) : 36.00$** (>= 100$)
- **Total HT après remise : 84.00$**
- **TVA (16%) : 13.44$**
- **NET À PAYER : 97.44$**

---

## Caractéristiques Techniques

### Concepts Java Utilisés (Niveau Débutant)
- Classes et objets
- Constructeurs
- Getters
- ArrayList
- Boucles for et for-each
- Conditions if-else
- Java Swing (JFrame, JPanel, JTextField, JButton, JTextArea)
- GridLayout et BorderLayout
- ActionListener et événements

### Structure Orientée Objet
1. **Classe Article** : Encapsule les données d'un article
2. **Classe Client** : Encapsule les données d'un client
3. **Classe Facture** : Gère la logique métier (calculs)
4. **Classe FactureGUI** : Gère l'interface graphique

### Points Forts de l'Implémentation
- Code simple et lisible
- Séparation des responsabilités
- Validation des entrées utilisateur
- Messages d'erreur clairs
- Interface intuitive en 3 étapes
- Formatage professionnel de la facture
