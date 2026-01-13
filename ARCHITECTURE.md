# Architecture du Système

## Diagramme de Classes Simplifié

```
┌─────────────────────────┐
│        Client           │
├─────────────────────────┤
│ - nom: String           │
│ - numeroTelephone: Str  │
├─────────────────────────┤
│ + getNom()              │
│ + getNumeroTelephone()  │
└─────────────────────────┘

┌─────────────────────────┐
│        Article          │
├─────────────────────────┤
│ - nom: String           │
│ - quantite: int         │
│ - prixUnitaire: double  │
├─────────────────────────┤
│ + getNom()              │
│ + getQuantite()         │
│ + getPrixUnitaire()     │
│ + getPrixTotal()        │
└─────────────────────────┘

┌─────────────────────────────────────┐
│           Facture                   │
├─────────────────────────────────────┤
│ - client: Client                    │
│ - articles: ArrayList<Article>      │
│ - TAUX_TVA = 0.16                   │
│ - TAUX_REMISE = 0.30                │
│ - SEUIL_REMISE = 100.0              │
├─────────────────────────────────────┤
│ + ajouterArticle(Article)           │
│ + calculerTotalHT(): double         │
│ + calculerRemise(): double          │
│ + calculerTVA(): double             │
│ + calculerNetAPayer(): double       │
│ + getClient(): Client               │
│ + getArticles(): ArrayList<Article> │
└─────────────────────────────────────┘
          │
          │ utilise
          ▼
┌─────────────────────────────┐
│      FactureGUI             │
│      (JFrame)               │
├─────────────────────────────┤
│ - factureActuelle: Facture  │
│ - nomClientField            │
│ - telephoneField            │
│ - nomArticleField           │
│ - quantiteField             │
│ - prixUnitaireField         │
│ - affichageArea             │
├─────────────────────────────┤
│ + ajouterArticle()          │
│ + calculerFacture()         │
│ + nouvelleFacture()         │
│ + main(String[])            │
└─────────────────────────────┘
```

## Flux d'Exécution

### 1. Démarrage
```
main() → Crée FactureGUI → Affiche fenêtre
```

### 2. Ajout d'un Article
```
Utilisateur remplit formulaire
    ↓
Clique "Ajouter Article"
    ↓
ajouterArticle() vérifie les données
    ↓
Si premier article: Crée Client et Facture
    ↓
Crée Article
    ↓
Ajoute à la facture
    ↓
Affiche confirmation
```

### 3. Calcul de la Facture
```
Utilisateur clique "Calculer Facture"
    ↓
calculerFacture() récupère données
    ↓
Facture.calculerTotalHT()
    ↓
Facture.calculerRemise()
    ↓
Facture.calculerTVA()
    ↓
Facture.calculerNetAPayer()
    ↓
Affiche facture formatée
```

## Formules de Calcul

### Total Hors Taxe (HT)
```
Total HT = Σ (quantité × prix unitaire)
```

### Remise
```
SI Total HT ≥ 100$ ALORS
    Remise = Total HT × 30%
SINON
    Remise = 0$
```

### TVA
```
TVA = (Total HT - Remise) × 16%
```

### Net à Payer
```
Net = Total HT - Remise + TVA
```

## Concepts OOP Utilisés

### Encapsulation
- Attributs privés (`private`)
- Accès via getters publics (`public`)

### Classes et Objets
- `Client`, `Article`, `Facture` = classes
- Chaque client, article = objet (instance)

### Collections
- `ArrayList<Article>` pour stocker plusieurs articles

### Héritage
- `FactureGUI extends JFrame` (héritage de Swing)

### Gestion d'Événements
- `ActionListener` pour les boutons
- Pattern Observer (événements GUI)

## Points Clés pour Expliquer

1. **Séparation des responsabilités** : Chaque classe a un rôle précis
2. **Réutilisabilité** : Les classes peuvent être utilisées indépendamment
3. **Maintenabilité** : Code organisé et facile à modifier
4. **Validation** : Vérification des données au niveau GUI
5. **Interface utilisateur** : Swing pour interaction conviviale
