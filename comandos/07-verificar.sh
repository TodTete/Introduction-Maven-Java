#!/usr/bin/env sh
# ============================================================
# 07-verificar
# Build completo + control de cobertura JaCoCo (minimo 70%). Fase: verify
# ============================================================
cd "$(dirname "$0")/.." || exit 1
./mvnw clean verify
