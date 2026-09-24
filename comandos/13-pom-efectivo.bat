@echo off
REM ============================================================
REM 13-pom-efectivo
REM Muestra el POM efectivo: tu pom.xml + valores heredados y por defecto
REM ============================================================
cd /d "%~dp0.."
call .\mvnw.cmd help:effective-pom
pause
