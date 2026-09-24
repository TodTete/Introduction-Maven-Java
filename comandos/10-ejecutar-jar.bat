@echo off
REM ============================================================
REM 10-ejecutar-jar
REM Ejecuta el fat jar generado (antes ejecuta 05-empaquetar)
REM ============================================================
cd /d "%~dp0.."
java -jar target\Introduction-Maven-1.0-SNAPSHOT-all.jar
pause
