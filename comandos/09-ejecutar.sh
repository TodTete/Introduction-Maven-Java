#!/usr/bin/env sh
# ============================================================
# 09-ejecutar
# Ejecuta la clase Main sin empaquetar (exec-maven-plugin)
# ============================================================
cd "$(dirname "$0")/.." || exit 1
./mvnw -q compile exec:java
