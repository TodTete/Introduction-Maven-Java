@echo off
REM ============================================================
REM 12-buscar-actualizaciones
REM Lista dependencias y plugins con versiones mas nuevas disponibles
REM ============================================================
cd /d "%~dp0.."
call .\mvnw.cmd versions:display-dependency-updates versions:display-plugin-updates
pause
