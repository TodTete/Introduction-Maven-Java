@echo off
REM ============================================================
REM 14-javadoc
REM Genera la documentacion HTML del codigo en target/reports/apidocs
REM ============================================================
cd /d "%~dp0.."
call .\mvnw.cmd javadoc:javadoc
pause
