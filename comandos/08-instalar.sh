#!/usr/bin/env sh
# ============================================================
# 08-instalar
# Copia el artefacto al repositorio local ~/.m2 para usarlo en otros proyectos. Fase: install
# ============================================================
cd "$(dirname "$0")/.." || exit 1
./mvnw clean install
