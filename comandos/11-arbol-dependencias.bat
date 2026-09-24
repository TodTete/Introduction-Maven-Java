@echo off
REM ============================================================
REM 11-arbol-dependencias
REM Muestra el arbol de dependencias directas y transitivas
REM ============================================================
cd /d "%~dp0.."
call .\mvnw.cmd dependency:tree
pause
