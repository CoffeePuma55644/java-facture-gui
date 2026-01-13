@echo off
echo === Compilation du projet ===
cd src
javac *.java

if %ERRORLEVEL% EQU 0 (
    echo Compilation réussie !
    echo.
    echo === Lancement de l'application ===
    java FactureGUI
) else (
    echo Erreur de compilation
    pause
    exit /b 1
)
