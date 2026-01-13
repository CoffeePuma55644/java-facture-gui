# Guide de Test - Application de Facturation

## Exemple de Scénario de Test

### Données Client
- **Nom**: Jean Dupont
- **Téléphone**: 555-1234

### Articles à Ajouter

#### Article 1
- **Nom**: Ordinateur Portable
- **Quantité**: 1
- **Prix unitaire**: 150.00

#### Article 2
- **Nom**: Souris
- **Quantité**: 2
- **Prix unitaire**: 15.00

#### Article 3
- **Nom**: Clavier
- **Quantité**: 1
- **Prix unitaire**: 25.00

### Résultat Attendu

```
========================================
       FACTURE - SUPER MARCHÉ
========================================

INFORMATIONS CLIENT:
Nom: Jean Dupont
Téléphone: 555-1234

ARTICLES ACHETÉS:
----------------------------------------
1. Ordinateur Portable
   Quantité: 1
   Prix unitaire: 150.00 $
   Total: 150.00 $

2. Souris
   Quantité: 2
   Prix unitaire: 15.00 $
   Total: 30.00 $

3. Clavier
   Quantité: 1
   Prix unitaire: 25.00 $
   Total: 25.00 $

========================================
CALCULS:
----------------------------------------
Total Hors Taxe: 205.00 $
Remise (30%): 61.50 $
TVA (16%): 22.96 $
========================================
NET À PAYER: 166.46 $
========================================
```

### Explication des Calculs

1. **Total Hors Taxe (HT)**: 150 + 30 + 25 = 205.00 $
2. **Remise**: Total HT ≥ 100$ → Remise de 30% appliquée = 205 × 0.30 = 61.50 $
3. **Montant après remise**: 205 - 61.50 = 143.50 $
4. **TVA**: (Total HT - Remise) × 16% = 143.50 × 0.16 = 22.96 $
5. **Net à Payer**: 143.50 + 22.96 = 166.46 $

## Test Sans Remise

### Données
- **Client**: Marie Martin, 555-5678
- **Article**: Pain (quantité: 2, prix: 5.00$)
- **Article**: Lait (quantité: 3, prix: 8.00$)

### Résultat
- **Total HT**: 34.00 $
- **Remise**: 0.00 $ (car Total HT < 100$)
- **TVA**: 5.44 $
- **Net à Payer**: 39.44 $
