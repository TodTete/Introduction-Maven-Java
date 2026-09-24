@echo off
REM ============================================================
REM 03-probar
REM Compila y ejecuta todas las pruebas JUnit (Surefire). Reportes en target/surefire-reports
REM ============================================================
cd /d "%~dp0.."
call .\mvnw.cmd test
pause
