@echo off
REM ============================================================
REM 08-instalar
REM Copia el artefacto al repositorio local ~/.m2 para usarlo en otros proyectos. Fase: install
REM ============================================================
cd /d "%~dp0.."
call .\mvnw.cmd clean install
pause
