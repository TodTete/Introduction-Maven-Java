#!/usr/bin/env sh
# ============================================================
# 10-ejecutar-jar
# Ejecuta el fat jar generado (antes ejecuta 05-empaquetar)
# ============================================================
cd "$(dirname "$0")/.." || exit 1
java -jar target/Introduction-Maven-1.0-SNAPSHOT-all.jar
