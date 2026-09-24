# Introducción a Maven

Proyecto de ejemplo para aprender **Apache Maven** con **Java 25**. Cubre dependencias, pruebas con JUnit 6, Mockito y AssertJ, cobertura con JaCoCo y la creación de artefactos (`.jar`), incluido un *fat jar* ejecutable.

## Requisitos

- JDK 25 (verifícalo con `java -version`)
- Maven 3.9 o superior es **opcional**: el proyecto incluye el Maven Wrapper (`mvnw`), que descarga la versión correcta de forma automática.

## Inicio rápido

```bash
# Windows
mvnw.cmd clean verify
java -jar target\Introduction-Maven-1.0-SNAPSHOT-all.jar

# Linux / macOS
./mvnw clean verify
java -jar target/Introduction-Maven-1.0-SNAPSHOT-all.jar
```

La carpeta [`comandos/`](comandos/README.md) tiene un script por cada comando importante, con su descripción.

## ¿Qué es Maven?

Maven es una herramienta de **construcción y gestión de dependencias** para Java. Se encarga de tres cosas:

1. **Descargar librerías.** Declaras la dependencia en `pom.xml` y Maven la descarga de [Maven Central](https://central.sonatype.com/), junto con sus dependencias transitivas.
2. **Estandarizar la estructura.** Todos los proyectos Maven organizan sus carpetas de la misma forma.
3. **Automatizar el build.** Compilar, probar, empaquetar e instalar se hace con un solo comando.

### Coordenadas

Todo artefacto se identifica por `groupId:artifactId:version`. En este proyecto son:

```
org.todtete:Introduction-Maven:1.0-SNAPSHOT
```

`SNAPSHOT` indica que la versión está en desarrollo. Una versión final se escribe sin ese sufijo, por ejemplo `1.0.0`.

### Ciclo de vida (fases)

```
validate → compile → test → package → verify → install → deploy
```

Al ejecutar una fase, Maven ejecuta también **todas las anteriores**. Por ejemplo, `mvn package` valida, compila, prueba y empaqueta. Aparte existe `clean`, que borra `target/`.

| Fase | Plugin en este proyecto | Resultado |
|---|---|---|
| validate | `maven-enforcer-plugin` | Comprueba que uses Java 25 o superior y Maven 3.9 o superior. |
| process-resources | `maven-resources-plugin` | Copia `src/main/resources` y reemplaza `${project.version}`. |
| compile | `maven-compiler-plugin` | Genera los `.class` en `target/classes`. |
| test | `maven-surefire-plugin` + JaCoCo | Ejecuta las pruebas y genera el reporte de cobertura. |
| package | `maven-jar-plugin`, `maven-source-plugin`, `maven-shade-plugin` | Genera el jar normal, el jar de fuentes y el fat jar. |
| verify | `jacoco:check` | Hace fallar el build si la cobertura es menor al 70 %. |
| install | `maven-install-plugin` | Copia el artefacto a `~/.m2/repository`. |

### Scopes de dependencias

| Scope | ¿Disponible al compilar? | ¿En las pruebas? | ¿Al ejecutar? | Ejemplo aquí |
|---|---|---|---|---|
| `compile` (por defecto) | ✅ | ✅ | ✅ | commons-lang3, jackson, slf4j |
| `runtime` | ❌ | ✅ | ✅ | logback-classic |
| `test` | ❌ (solo en las pruebas) | ✅ | ❌ | junit, assertj, mockito |
| `provided` | ✅ | ✅ | ❌ (lo aporta el servidor) | — |

## Estructura del proyecto

```
Introduction-Maven/
├── pom.xml                       ← Configuración de Maven (comentada línea a línea)
├── mvnw / mvnw.cmd / .mvn/       ← Maven Wrapper
├── comandos/                     ← Scripts .bat y .sh con los comandos de Maven
└── src/
    ├── main/
    │   ├── java/org/todtete/
    │   │   ├── Main.java                    ← Punto de entrada
    │   │   ├── model/Persona.java           ← Record con validaciones
    │   │   ├── repository/                  ← Interfaz y una implementación en memoria
    │   │   ├── service/                     ← Calculadora y PersonaService
    │   │   └── util/                        ← TextoUtil (Commons Lang), JsonUtil (Jackson), InfoAplicacion
    │   └── resources/
    │       ├── app.properties               ← Filtrado por Maven (recibe la versión del pom)
    │       └── logback.xml                  ← Configuración de logs
    └── test/
        ├── java/org/todtete/                ← Pruebas (misma estructura de paquetes)
        └── resources/logback-test.xml       ← Logs durante las pruebas
```

`target/` lo genera Maven y **no se versiona** (está en `.gitignore`).

## Dependencias incluidas

| Librería | Uso |
|---|---|
| **Apache Commons Lang 3** | Utilidades de texto (`StringUtils`). |
| **Jackson Databind** | Conversión de objetos Java a JSON y de JSON a objetos. |
| **SLF4J + Logback** | Logging. |
| **JUnit Jupiter 6** | Pruebas unitarias: `@Test`, `@ParameterizedTest`, `@Nested`. |
| **AssertJ** | Aserciones fluidas, como `assertThat(x).isEqualTo(y)`. |
| **Mockito** | Mocks para aislar la clase que se está probando. |

## Pruebas

| Clase | Qué demuestra |
|---|---|
| `CalculadoraTest` | JUnit básico, `@BeforeEach`, `@DisplayName`, `@Nested`, `@CsvSource`, `@ValueSource` y `assertThrows`. |
| `PersonaServiceTest` | Mockito: `@Mock`, `@InjectMocks`, `when(...)`, `verify(...)` y el patrón Given/When/Then. |
| `PersonaTest` | Validaciones del record con AssertJ. |
| `UtilTest` | `@NullAndEmptySource`, Jackson y filtrado de recursos. |

- Reporte de pruebas: `target/surefire-reports/`
- Reporte de cobertura: `target/site/jacoco/index.html` (se abre en el navegador)

## Cómo se crea el artefacto, paso a paso

1. Ejecuta `mvnw clean package`.
2. Maven compila el código, ejecuta las pruebas y, si todas pasan, empaqueta.
3. En `target/` quedan:
   - `Introduction-Maven-1.0-SNAPSHOT.jar`: solo el código del proyecto. En su `MANIFEST.MF` figura `Main-Class`.
   - `Introduction-Maven-1.0-SNAPSHOT-sources.jar`: el código fuente.
   - `Introduction-Maven-1.0-SNAPSHOT-all.jar`: el fat jar con todas las dependencias.
4. Ejecuta el fat jar con `java -jar target/Introduction-Maven-1.0-SNAPSHOT-all.jar`.
5. Opcionalmente, ejecuta `mvnw install` para que otro proyecto pueda usar este como dependencia:

```xml
<dependency>
    <groupId>org.todtete</groupId>
    <artifactId>Introduction-Maven</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

## Ejercicios sugeridos

1. Cambia `<version>` en el `pom.xml`, vuelve a empaquetar y observa que el nombre del jar y el mensaje de inicio cambian.
2. Agrega una dependencia nueva, por ejemplo Guava, y úsala en una clase.
3. Borra una prueba y ejecuta `mvnw verify`: el control de cobertura puede hacer fallar el build.
4. Ejecuta `mvnw dependency:tree` y busca las dependencias transitivas de Jackson.
5. Crea tu propio perfil en `<profiles>` y actívalo con `-P`.
