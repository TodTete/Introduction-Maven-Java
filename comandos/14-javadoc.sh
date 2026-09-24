#!/usr/bin/env sh
# ============================================================
# 14-javadoc
# Genera la documentacion HTML del codigo en target/reports/apidocs
# ============================================================
cd "$(dirname "$0")/.." || exit 1
./mvnw javadoc:javadoc
