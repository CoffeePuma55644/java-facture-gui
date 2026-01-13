# Résumé du Projet - Système de Facturation

## Description
Application Java avec interface graphique (Swing) pour la gestion de facturation d'un super marché.

## Architecture (Programmation Orientée Objet)

### Classes Principales

1. **Article.java** (31 lignes)
   - Attributs: nom, quantité, prixUnitaire
   - Méthode: getTotal() pour calculer le total de l'article

2. **Client.java** (20 lignes)
   - Attributs: nom, telephone
   - Getters simples

3. **Facture.java** (63 lignes)
   - Gère une collection d'articles
   - Calcule: Total HT, Remise (30% si ≥100$), TVA (16%), Net à payer
   - Constantes: TAUX_TVA = 0.16, TAUX_REMISE = 0.30, SEUIL_REMISE = 100.0

4. **FormatFacture.java** (56 lignes) - NOUVELLE CLASSE
   - Classe utilitaire pour formater l'affichage des factures
   - Méthodes: formaterFacture(), ajouterEntete(), ajouterInfoClient(), ajouterListeArticles(), ajouterCalculs()
   - Sépare la logique de formatage de l'interface graphique

5. **FactureGUI.java** (228 lignes) - OPTIMISÉE
   - Interface graphique avec Swing
   - Formulaires pour client et articles
   - Boutons: Ajouter Article, Afficher Facture, Nouvelle Facture
   - Zone de texte pour afficher la facture formatée
   - Méthode utilitaire: ajouterChampSaisie() pour réduire la duplication

## Fonctionnalités Implémentées ✓

- ✓ Saisie des informations client (nom, téléphone)
- ✓ Saisie d'articles multiples (nom, quantité, prix unitaire)
- ✓ Calcul automatique de la TVA (16%)
- ✓ Application automatique de remise 30% si Total HT ≥ 100$
- ✓ Affichage complet de la facture avec:
  - Informations client
  - Liste détaillée des articles
  - Total Hors Taxe
  - Montant de la remise
  - Montant de la TVA
  - Net à payer

## Validation des Données

- Vérification des champs vides
- Validation des nombres (quantité et prix)
- Messages d'erreur appropriés avec JOptionPane
- Vérification qu'au moins un article est ajouté

## Formules de Calcul

```
Total HT = Σ(quantité × prix unitaire) pour tous les articles

Remise = {
  Total HT × 0.30  si Total HT ≥ 100$
  0                sinon
}

TVA = (Total HT - Remise) × 0.16

Net à Payer = Total HT - Remise + TVA
```

## Exemples de Calculs

### Exemple 1: Avec Remise (Total ≥ 100$)
- Total HT: 205.00$
- Remise (30%): 61.50$
- TVA (16%): 22.96$
- **Net à Payer: 166.46$**

### Exemple 2: Sans Remise (Total < 100$)
- Total HT: 34.00$
- Remise: 0.00$
- TVA (16%): 5.44$
- **Net à Payer: 39.44$**

## Points Forts du Code

1. **Simple et Clair**: Code facile à comprendre pour un débutant
2. **OOP**: Utilisation correcte des classes et encapsulation
3. **Séparation des Responsabilités**: Classe FormatFacture dédiée au formatage
4. **Code DRY**: Méthode utilitaire pour éviter la duplication
5. **GUI Fonctionnelle**: Interface intuitive avec Swing
6. **Validation**: Gestion des erreurs utilisateur
7. **Calculs Précis**: Formules mathématiques correctes
8. **Sans Dépendances**: Utilise uniquement les bibliothèques Java standard
9. **Maintenable**: Code bien organisé et facile à modifier

## Compilation et Exécution

```bash
# Compilation
cd src
javac *.java

# Exécution
java FactureGUI
```

## Sécurité
✓ Aucune vulnérabilité détectée (CodeQL)
✓ Pas de failles de sécurité
✓ Validation appropriée des entrées

---

**Total:** 398 lignes de code Java
**Langage:** Java (Swing)
**Niveau:** Débutant/Intermédiaire

## Optimisations Récentes

- ✅ Extraction du formatage dans FormatFacture.java (séparation des responsabilités)
- ✅ Réduction de FactureGUI.java de 255 à 228 lignes (-10.6%)
- ✅ Méthode afficherFacture() réduite de ~70 à 28 lignes (-60%)
- ✅ Ajout de méthode utilitaire ajouterChampSaisie() pour réduire duplication
- ✅ Code maintenu simple et accessible (pas de lambdas, pas de streams)
