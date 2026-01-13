# Système de Facturation - Super Marché

Un programme Java avec interface graphique (Swing) pour gérer la facturation d'un supermarché. Ce projet utilise la programmation orientée objet (OOP) et est conçu pour être simple et compréhensible.

## 📋 Fonctionnalités

- ✅ Enregistrer les informations du client (nom, téléphone)
- ✅ Ajouter plusieurs articles (nom, quantité, prix unitaire)
- ✅ Calcul automatique de la TVA (16%)
- ✅ Remise de 30% si Total HT ≥ 100$
- ✅ Affichage complet de la facture

## 🚀 Démarrage Rapide

### Compilation
```bash
cd src
javac *.java
```

### Exécution
```bash
java FactureGUI
```

## 📚 Documentation

- **[GUIDE.md](GUIDE.md)** : Guide complet avec structure du projet et exemples de calcul
- **[EXEMPLE_UTILISATION.md](EXEMPLE_UTILISATION.md)** : Scénarios d'utilisation détaillés

## 🏗️ Structure

```
src/
├── Client.java        # Classe Client (nom, téléphone)
├── Article.java       # Classe Article (nom, quantité, prix)
├── Facture.java       # Classe Facture (gestion calculs)
├── FactureGUI.java    # Interface graphique Swing
└── TestFacture.java   # Tests de validation
```

## 💡 Exemple d'Utilisation

1. Lancer l'application
2. Entrer nom et téléphone du client
3. Ajouter des articles un par un
4. Cliquer sur "Calculer Facture"
5. Voir la facture complète avec tous les détails

## ✨ Caractéristiques Techniques

- **Langage** : Java
- **GUI** : Java Swing
- **Paradigme** : Programmation Orientée Objet
- **Concepts** : Encapsulation, Classes, Collections (ArrayList)

## 📖 Exemple de Calcul

**Avec remise (Total ≥ 100$)** :
- Total HT : 200.00$
- Remise (30%) : -60.00$
- TVA (16%) : +22.40$
- **Net à payer : 162.40$**

---

*Projet étudiant - Programmation Java avec GUI*
