# Comandos de Maven

Scripts listos para usar. Cada uno existe en dos versiones:

- `.bat` para Windows (doble clic o desde la terminal)
- `.sh` para Linux, macOS o Git Bash (`sh comandos/03-probar.sh`)

Todos se mueven a la raíz del proyecto y usan el **Maven Wrapper** (`mvnw` / `mvnw.cmd`), así que no hace falta tener Maven instalado. Si ya lo tienes instalado, puedes cambiar `mvnw` por `mvn`.

| Script | Comando | Qué hace |
|---|---|---|
| `01-limpiar` | `mvn clean` | Borra `target/` con todo lo que generaron compilaciones anteriores. |
| `02-compilar` | `mvn compile` | Valida el proyecto y compila `src/main/java` en `target/classes`. |
| `03-probar` | `mvn test` | Compila y ejecuta todas las pruebas JUnit. Genera los reportes en `target/surefire-reports` y la cobertura en `target/site/jacoco/index.html`. |
| `04-probar-una-clase` | `mvn test -Dtest=CalculadoraTest` | Ejecuta solo una clase de prueba. También acepta un método: `-Dtest=CalculadoraTest#sumar`. |
| `05-empaquetar` | `mvn clean package` | Genera los **artefactos** en `target/` (detalle en la tabla de abajo). |
| `06-empaquetar-rapido` | `mvn clean package -Prapido` | Hace lo mismo que el anterior pero sin pruebas ni cobertura, usando el perfil `rapido` del `pom.xml`. |
| `07-verificar` | `mvn clean verify` | Hace el build completo y exige una cobertura de líneas de al menos 70 % (JaCoCo). Es el comando recomendado para CI. |
| `08-instalar` | `mvn clean install` | Copia el artefacto a tu repositorio local `~/.m2/repository` para usarlo como dependencia en otros proyectos. |
| `09-ejecutar` | `mvn compile exec:java` | Ejecuta `Main` sin crear el jar. |
| `10-ejecutar-jar` | `java -jar target/...-all.jar` | Ejecuta el fat jar. Antes hay que correr `05-empaquetar`. |
| `11-arbol-dependencias` | `mvn dependency:tree` | Muestra el árbol de dependencias directas y transitivas. |
| `12-buscar-actualizaciones` | `mvn versions:display-*-updates` | Lista las dependencias y plugins que tienen una versión más nueva. |
| `13-pom-efectivo` | `mvn help:effective-pom` | Muestra el POM final: tu `pom.xml` junto con todo lo que Maven hereda y pone por defecto. |
| `14-javadoc` | `mvn javadoc:javadoc` | Genera la documentación HTML del código. |

## Artefactos que genera `package`

| Archivo | Contenido |
|---|---|
| `Introduction-Maven-1.0-SNAPSHOT.jar` | Solo las clases del proyecto. Es el jar "delgado", pensado para usarse como librería. |
| `Introduction-Maven-1.0-SNAPSHOT-sources.jar` | El código fuente. |
| `Introduction-Maven-1.0-SNAPSHOT-all.jar` | El **fat jar**: las clases del proyecto junto con todas las dependencias. Se ejecuta con `java -jar`. |

## Opciones útiles para cualquier comando

| Opción | Efecto |
|---|---|
| `-DskipTests` | Compila las pruebas, pero no las ejecuta. |
| `-Dmaven.test.skip=true` | No compila ni ejecuta las pruebas. |
| `-P<perfil>` | Activa un perfil del `pom.xml`, por ejemplo `-Prapido`. |
| `-o` | Modo offline: no descarga nada de internet. |
| `-U` | Fuerza a actualizar las dependencias SNAPSHOT. |
| `-q` / `-X` | Salida silenciosa o salida de depuración. |
| `-B` | Modo batch, sin colores ni interacción (útil en CI). |
