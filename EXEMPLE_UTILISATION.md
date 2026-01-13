# Exemple d'Utilisation du Système de Facturation

## Interface Graphique

L'application présente une interface divisée en plusieurs sections :

### 1. Section "Informations Client"
```
┌─────────────────────────────────────────┐
│ Informations Client                     │
├─────────────────────────────────────────┤
│ Nom du client:      [_______________]   │
│ Numéro de téléphone:[_______________]   │
└─────────────────────────────────────────┘
```

### 2. Section "Ajouter un Article"
```
┌─────────────────────────────────────────┐
│ Ajouter un Article                      │
├─────────────────────────────────────────┤
│ Nom de l'article:   [_______________]   │
│ Quantité:           [_______________]   │
│ Prix unitaire ($):  [_______________]   │
└─────────────────────────────────────────┘
```

### 3. Boutons d'Action
```
[Ajouter Article] [Calculer Facture] [Nouvelle Facture]
```

### 4. Zone d'Affichage de la Facture
```
┌──────────────────────────────────────────────────┐
│                                                  │
│  La facture finale s'affiche ici                 │
│                                                  │
└──────────────────────────────────────────────────┘
```

## Scénario d'Utilisation Complet

### Étape 1 : Démarrer l'application
```bash
cd src
java FactureGUI
```

### Étape 2 : Saisir les informations du client
- Nom du client: **Jean Dupont**
- Numéro de téléphone: **0612345678**

### Étape 3 : Ajouter des articles

**Article 1 :**
- Nom: Pain
- Quantité: 2
- Prix unitaire: 5.00
- Cliquer sur **"Ajouter Article"**

**Article 2 :**
- Nom: Lait
- Quantité: 3
- Prix unitaire: 8.00
- Cliquer sur **"Ajouter Article"**

**Article 3 :**
- Nom: Fromage
- Quantité: 1
- Prix unitaire: 50.00
- Cliquer sur **"Ajouter Article"**

### Étape 4 : Calculer la facture
Cliquer sur **"Calculer Facture"**

### Résultat Affiché

```
===========================================
          FACTURE - SUPER MARCHÉ
===========================================

Client: Jean Dupont
Téléphone: 0612345678

-------------------------------------------
ARTICLES ACHETÉS:
-------------------------------------------
Pain                 x2     $5.00    = $10.00
Lait                 x3     $8.00    = $24.00
Fromage              x1     $50.00   = $50.00

-------------------------------------------
RÉSUMÉ:
-------------------------------------------
Total Hors Taxe:        $84.00
Remise (30%):           -$0.00
TVA (16%):              $13.44
-------------------------------------------
NET À PAYER:            $97.44
===========================================
```

## Exemple Avec Remise

Si le Total HT est ≥ 100$ :

**Articles :**
- Ordinateur : 1 × 150.00$ = 150.00$
- Souris : 2 × 25.00$ = 50.00$

**Résultat :**
```
===========================================
          FACTURE - SUPER MARCHÉ
===========================================

Client: Marie Martin
Téléphone: 0698765432

-------------------------------------------
ARTICLES ACHETÉS:
-------------------------------------------
Ordinateur           x1     $150.00  = $150.00
Souris               x2     $25.00   = $50.00

-------------------------------------------
RÉSUMÉ:
-------------------------------------------
Total Hors Taxe:        $200.00
Remise (30%):           -$60.00
TVA (16%):              $22.40
-------------------------------------------
NET À PAYER:            $162.40
===========================================
```

## Messages d'Erreur

L'application affiche des messages d'alerte dans les cas suivants :

1. **Champs vides** : "Veuillez remplir tous les champs de l'article!"
2. **Valeurs négatives** : "La quantité et le prix doivent être positifs!"
3. **Format invalide** : "Veuillez entrer des valeurs numériques valides!"
4. **Pas d'articles** : "Veuillez ajouter au moins un article!"

## Recommencer une Nouvelle Facture

Cliquer sur **"Nouvelle Facture"** pour :
- Effacer tous les champs
- Réinitialiser la facture
- Commencer une nouvelle transaction
