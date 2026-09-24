@echo off
REM ============================================================
REM 07-verificar
REM Build completo + control de cobertura JaCoCo (minimo 70%%). Fase: verify
REM ============================================================
cd /d "%~dp0.."
call .\mvnw.cmd clean verify
pause
