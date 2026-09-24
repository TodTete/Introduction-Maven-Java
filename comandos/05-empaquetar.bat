@echo off
REM ============================================================
REM 05-empaquetar
REM Crea los artefactos en target/: jar normal, -sources.jar y fat jar -all.jar. Fase: package
REM ============================================================
cd /d "%~dp0.."
call .\mvnw.cmd clean package
pause
