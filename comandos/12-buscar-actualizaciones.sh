#!/usr/bin/env sh
# ============================================================
# 12-buscar-actualizaciones
# Lista dependencias y plugins con versiones mas nuevas disponibles
# ============================================================
cd "$(dirname "$0")/.." || exit 1
./mvnw versions:display-dependency-updates versions:display-plugin-updates
