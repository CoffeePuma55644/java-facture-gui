#!/bin/bash

echo "=== Compilation du projet ==="
cd src
javac *.java

if [ $? -eq 0 ]; then
    echo "✓ Compilation réussie!"
    echo ""
    echo "=== Lancement de l'application ==="
    java FactureGUI
else
    echo "✗ Erreur de compilation"
    exit 1
fi
