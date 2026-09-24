#!/usr/bin/env sh
# ============================================================
# 04-probar-una-clase
# Ejecuta solo una clase de prueba (cambia CalculadoraTest por la que quieras)
# ============================================================
cd "$(dirname "$0")/.." || exit 1
./mvnw test -Dtest=CalculadoraTest
