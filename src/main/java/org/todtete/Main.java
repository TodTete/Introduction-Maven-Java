package org.todtete;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.todtete.model.Persona;
import org.todtete.repository.PersonaRepositoryEnMemoria;
import org.todtete.service.Calculadora;
import org.todtete.service.PersonaService;
import org.todtete.util.InfoAplicacion;
import org.todtete.util.JsonUtil;
import org.todtete.util.TextoUtil;

/**
 * Punto de entrada de la aplicación.
 *
 * <p>Demuestra el uso de las dependencias declaradas en el {@code pom.xml}:
 * <ul>
 *   <li>SLF4J + Logback para logging.</li>
 *   <li>Apache Commons Lang para utilidades de texto.</li>
 *   <li>Jackson para convertir objetos a JSON.</li>
 * </ul>
 *
 * <p>Formas de ejecutarla:
 * <pre>
 *   mvn compile exec:java                                       (sin empaquetar)
 *   java -jar target/Introduction-Maven-1.0-SNAPSHOT-all.jar    (fat jar)
 * </pre>
 */
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        // 1) Información del artefacto: la versión viene del pom.xml gracias al filtrado de recursos
        InfoAplicacion info = InfoAplicacion.cargar();
        log.info("Iniciando {} v{} (Java {})", info.nombre(), info.version(), Runtime.version().feature());

        // 2) Lógica simple
        Calculadora calc = new Calculadora();
        IO.println("5 + 3 = " + calc.sumar(5, 3));
        IO.println("10 / 4 = " + calc.dividir(10, 4));
        IO.println("5! = " + calc.factorial(5));

        // 3) Uso de Commons Lang
        IO.println(TextoUtil.capitalizarPalabras("hola mundo desde maven"));
        IO.println("¿'reconocer' es palíndromo? " + TextoUtil.esPalindromo("reconocer"));

        // 4) Servicio + repositorio + Jackson
        PersonaService service = new PersonaService(new PersonaRepositoryEnMemoria());
        service.registrar(new Persona("Ana", "ana@correo.com", 28));
        service.registrar(new Persona("Luis", "luis@correo.com", 17));

        IO.println("Mayores de edad: " + service.mayoresDeEdad());
        IO.println("JSON: " + JsonUtil.aJson(service.listar()));

        log.info("Fin de la ejecución");
    }
}
