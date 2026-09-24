#!/usr/bin/env sh
# ============================================================
# 03-probar
# Compila y ejecuta todas las pruebas JUnit (Surefire). Reportes en target/surefire-reports
# ============================================================
cd "$(dirname "$0")/.." || exit 1
./mvnw test
