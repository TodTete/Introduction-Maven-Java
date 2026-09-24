#!/usr/bin/env sh
# ============================================================
# 02-compilar
# Compila src/main/java hacia target/classes. Fases: validate -> compile
# ============================================================
cd "$(dirname "$0")/.." || exit 1
./mvnw compile
