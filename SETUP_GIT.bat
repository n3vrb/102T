@echo off
title CSE 102T - Git Setup

echo.
echo ================================
echo  CSE 102T - Git Repo Kurulumu
echo ================================
echo.

cd /d "%~dp0"
echo Klasor: %CD%
echo.

if exist ".git" (
    echo Eski .git siliniyor...
    attrib -r -s -h ".git\*.*" /s /d >nul 2>&1
    rmdir /s /q ".git" >nul 2>&1
    echo Silindi.
)

echo [1/4] Git baslatiliyor...
git init
git branch -M main

echo [2/4] Kullanici ayarlaniyor...
git config user.name "Enes Bilgin"
git config user.email "bilgin.enea@gmail.com"

echo [3/4] Dosyalar ekleniyor...
git add .

echo [4/4] Commit yapiliyor...
git commit -m "Initial commit: CSE 102T Final Study Materials"

echo.
echo ================================
echo  TAMAMLANDI!
echo ================================
echo.
git log --oneline
echo.
echo GitHub'a yuklemek icin:
echo   git remote add origin ^<repo-url^>
echo   git push -u origin main
echo.
pause
