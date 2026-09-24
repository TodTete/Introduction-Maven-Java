@echo off
REM ============================================================
REM 01-limpiar
REM Borra la carpeta target/ (resultados de compilaciones anteriores). Fase: clean
REM ============================================================
cd /d "%~dp0.."
call .\mvnw.cmd clean
pause
