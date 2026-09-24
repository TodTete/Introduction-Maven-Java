#!/usr/bin/env sh
# ============================================================
# 13-pom-efectivo
# Muestra el POM efectivo: tu pom.xml + valores heredados y por defecto
# ============================================================
cd "$(dirname "$0")/.." || exit 1
./mvnw help:effective-pom
