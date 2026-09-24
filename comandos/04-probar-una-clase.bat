@echo off
REM ============================================================
REM 04-probar-una-clase
REM Ejecuta solo una clase de prueba (cambia CalculadoraTest por la que quieras)
REM ============================================================
cd /d "%~dp0.."
call .\mvnw.cmd test -Dtest=CalculadoraTest
pause
