# Système de Facturation - Super Marché

Un programme Java avec interface graphique (Java Swing) pour gérer la facturation d'un supermarché.

## Fonctionnalités

Le programme permet de :
- Enregistrer les informations du client (nom, numéro de téléphone)
- Ajouter plusieurs articles avec leurs détails (nom, quantité, prix unitaire)
- Calculer automatiquement la TVA (16%)
- Appliquer une remise de 30% sur le Total Hors Taxe quand il est ≥ 100$
- Afficher une facture complète avec tous les détails

## Structure du Projet

### Classes principales

1. **Client.java** : Représente un client
   - Attributs : nom, numéro de téléphone
   - Méthodes : getters pour accéder aux informations

2. **Article.java** : Représente un article acheté
   - Attributs : nom, quantité, prix unitaire
   - Méthodes : getters et calcul du prix total

3. **Facture.java** : Gère une facture complète
   - Attributs : client, liste d'articles
   - Méthodes : 
     - ajouterArticle()
     - calculerTotalHT()
     - calculerRemise() (30% si total ≥ 100$)
     - calculerTVA() (16%)
     - calculerNetAPayer()

4. **FactureGUI.java** : Interface graphique Swing
   - Formulaire pour les informations client
   - Formulaire pour ajouter des articles
   - Boutons : Ajouter Article, Calculer Facture, Nouvelle Facture
   - Zone d'affichage de la facture

## Compilation et Exécution

### Compiler le programme

```bash
cd src
javac *.java
```

### Lancer l'application

```bash
java FactureGUI
```

### Tester la logique (optionnel)

```bash
java TestFacture
```

## Utilisation

1. **Entrer les informations du client** :
   - Nom du client
   - Numéro de téléphone

2. **Ajouter des articles** :
   - Nom de l'article
   - Quantité
   - Prix unitaire
   - Cliquer sur "Ajouter Article"
   - Répéter pour chaque article

3. **Calculer la facture** :
   - Cliquer sur "Calculer Facture"
   - La facture complète s'affiche avec tous les détails

4. **Nouvelle facture** :
   - Cliquer sur "Nouvelle Facture" pour recommencer

## Exemple de Calcul

### Exemple 1 : Sans remise (Total HT < 100$)
- Pain : 2 × 5.00$ = 10.00$
- Lait : 3 × 8.00$ = 24.00$
- **Total HT** : 34.00$
- **Remise** : 0.00$ (car < 100$)
- **TVA (16%)** : 5.44$
- **Net à payer** : 39.44$

### Exemple 2 : Avec remise (Total HT ≥ 100$)
- Ordinateur : 1 × 150.00$ = 150.00$
- Souris : 2 × 25.00$ = 50.00$
- **Total HT** : 200.00$
- **Remise (30%)** : 60.00$
- **Sous-total** : 140.00$
- **TVA (16%)** : 22.40$
- **Net à payer** : 162.40$

## Notes

Ce programme utilise les concepts de base de la programmation orientée objet :
- Encapsulation (attributs privés avec getters)
- Classes et objets
- Collections (ArrayList)
- Héritage des classes Swing (JFrame)
- Gestion des événements

Le code est volontairement simple et clair pour être facilement compréhensible par un étudiant débutant.
