#!/usr/bin/env sh
# ============================================================
# 05-empaquetar
# Crea los artefactos en target/: jar normal, -sources.jar y fat jar -all.jar. Fase: package
# ============================================================
cd "$(dirname "$0")/.." || exit 1
./mvnw clean package
