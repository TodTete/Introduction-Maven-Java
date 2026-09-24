#!/usr/bin/env sh
# ============================================================
# 01-limpiar
# Borra la carpeta target/ (resultados de compilaciones anteriores). Fase: clean
# ============================================================
cd "$(dirname "$0")/.." || exit 1
./mvnw clean
