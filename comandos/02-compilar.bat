@echo off
REM ============================================================
REM 02-compilar
REM Compila src/main/java hacia target/classes. Fases: validate -> compile
REM ============================================================
cd /d "%~dp0.."
call .\mvnw.cmd compile
pause
