#!/usr/bin/env sh
# ============================================================
# 06-empaquetar-rapido
# Empaqueta saltando pruebas y cobertura (perfil rapido del pom.xml)
# ============================================================
cd "$(dirname "$0")/.." || exit 1
./mvnw clean package -Prapido
