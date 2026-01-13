#!/bin/bash

echo "==================================="
echo "   Système de Facturation"
echo "   Super Marché"
echo "==================================="
echo ""

# Navigate to src directory
cd src

# Check if files are already compiled
if [ ! -f "FactureGUI.class" ]; then
    echo "Compilation des fichiers Java..."
    javac *.java
    if [ $? -ne 0 ]; then
        echo "Erreur lors de la compilation!"
        exit 1
    fi
    echo "Compilation réussie!"
    echo ""
fi

# Run the application
echo "Lancement de l'application..."
java FactureGUI
