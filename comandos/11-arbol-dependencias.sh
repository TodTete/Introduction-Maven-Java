#!/usr/bin/env sh
# ============================================================
# 11-arbol-dependencias
# Muestra el arbol de dependencias directas y transitivas
# ============================================================
cd "$(dirname "$0")/.." || exit 1
./mvnw dependency:tree
