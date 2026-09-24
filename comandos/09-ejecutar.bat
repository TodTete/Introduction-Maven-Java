@echo off
REM ============================================================
REM 09-ejecutar
REM Ejecuta la clase Main sin empaquetar (exec-maven-plugin)
REM ============================================================
cd /d "%~dp0.."
call .\mvnw.cmd -q compile exec:java
pause
