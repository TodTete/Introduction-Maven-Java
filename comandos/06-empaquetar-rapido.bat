@echo off
REM ============================================================
REM 06-empaquetar-rapido
REM Empaqueta saltando pruebas y cobertura (perfil rapido del pom.xml)
REM ============================================================
cd /d "%~dp0.."
call .\mvnw.cmd clean package -Prapido
pause
