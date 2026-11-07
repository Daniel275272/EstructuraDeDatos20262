package Momento;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class GestorSistema {

    private Scanner teclado = new Scanner(System.in);
    private Universidad universidad = new Universidad();

    private Queue<String> colaSolicitudes = new LinkedList<>();

    private Stack<Operacion> pilaDeshacer = new Stack<>();
    private Stack<Operacion> pilaRehacer = new Stack<>();

    private Stack<String> pilaReportesAtras = new Stack<>();
    private Stack<String> pilaReportesAdelante = new Stack<>();

    private String reporteActual = "";

    // CLASE OPERACION
    private class Operacion {
        String tipo;
        Estudiante estudiante;
        Materia materia;
        Aula aula;

        String codigoMateria;

        int dia;
        int hora;
        int duracion;

        int semestre;
        int posicion;
        Double notaAnterior;
        double notaNueva;

        int origen;
        int destino;
        int distanciaAnterior;
        int distanciaNueva;

        Operacion(String tipo) {
            this.tipo = tipo;
        }
    }

    // GUARDAR OPERACION
    private void guardarOperacion(Operacion operacion) {
        pilaDeshacer.push(operacion);
        pilaRehacer.clear();
    }

    // 1. REGISTRAR ESTUDIANTE
    public void registrarEstudiante() {
        System.out.println("\n--- REGISTRO DE ESTUDIANTE ---");

        System.out.print("ID: ");
        String id = teclado.nextLine();

        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();

        System.out.print("Email: ");
        String email = teclado.nextLine();

        System.out.print("Semestre actual: ");
        int semestre = leerEntero();

        if (semestre < 1 || semestre > 10) {
            System.out.println("Semestre invalido.");
            return;
        }

        Estudiante estudiante =
                new Estudiante(nombre, id, email, semestre);

        if (universidad.registrarEstudiante(estudiante)) {

            Operacion operacion =
                    new Operacion("REGISTRAR_ESTUDIANTE");

            operacion.estudiante = estudiante;

            guardarOperacion(operacion);

            System.out.println("Estudiante registrado exitosamente.");
        } else {
            System.out.println("Ya existe un estudiante con ese ID.");
        }
    }

    // 2. BUSCAR ESTUDIANTE
    public void buscarEstudiante() {
        System.out.println("\n--- BUSCAR ESTUDIANTE ---");

        System.out.print("ID: ");
        String id = teclado.nextLine();

        Estudiante estudiante = universidad.buscarEstudiante(id);

        if (estudiante == null) {
            System.out.println(
                "Error: no existe estudiante con ID: " + id
            );
            return;
        }

        estudiante.mostrarInformacion();

        Double promedio = estudiante.calcularPromedioAcumulado();

        if (promedio == null) {
            System.out.println("Promedio acumulado: Sin notas registradas.");
        } else {
            System.out.printf("Promedio acumulado: %.2f%n", promedio);
        }
    }

    // 3. LISTAR ESTUDIANTES
    public void listarEstudiantes() {
        System.out.println("\n--- LISTA DE ESTUDIANTES ---");
        universidad.mostrarEstudiantes();
    }

    // 4. ELIMINAR ESTUDIANTE
    public void eliminarEstudiante() {
        System.out.println("\n--- ELIMINAR ESTUDIANTE ---");

        System.out.print("ID del estudiante: ");
        String id = teclado.nextLine();

        Estudiante estudiante = universidad.buscarEstudiante(id);

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        if (universidad.eliminarEstudiante(id)) {

            Operacion operacion =
                    new Operacion("ELIMINAR_ESTUDIANTE");

            operacion.estudiante = estudiante;

            guardarOperacion(operacion);

            System.out.println("Estudiante eliminado exitosamente.");
        }
    }

    // 5. CREAR MATERIA
    public void registrarMateria() {
        System.out.println("\n--- CREAR MATERIA ---");

        System.out.print("Codigo: ");
        String codigo = teclado.nextLine();

        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();

        System.out.print("Cupos maximos: ");
        int cupos = leerEntero();

        System.out.print("Creditos: ");
        int creditos = leerEntero();

        if (cupos <= 0 || creditos <= 0) {
            System.out.println("Cupos y creditos deben ser positivos.");
            return;
        }

        Materia materia =
                new Materia(codigo, nombre, cupos, creditos);

        if (universidad.registrarMateria(materia)) {

            Operacion operacion =
                    new Operacion("REGISTRAR_MATERIA");

            operacion.materia = materia;
            operacion.codigoMateria = codigo;

            guardarOperacion(operacion);

            System.out.println("Materia registrada exitosamente.");
        } else {
            System.out.println("No se pudo registrar la materia.");
        }
    }

    // 6. AGREGAR PRERREQUISITO
    public void agregarPrerrequisito() {
        System.out.println("\n--- AGREGAR PRERREQUISITO ---");

        System.out.print("Codigo de la materia principal: ");
        String codigoPrincipal = teclado.nextLine();

        System.out.print("Codigo del prerrequisito: ");
        String codigoRequisito = teclado.nextLine();

        Materia principal = universidad.buscarMateria(codigoPrincipal);
        Materia requisito = universidad.buscarMateria(codigoRequisito);

        if (principal == null || requisito == null) {
            System.out.println("No se encontraron las materias.");
            return;
        }

        if (principal.agregarPrerrequisito(requisito)) {
            System.out.println("Prerrequisito agregado exitosamente.");
        } else {
            System.out.println("No se pudo agregar el prerrequisito.");
        }
    }

    // 7. MOSTRAR PRERREQUISITOS
    public void mostrarPrerrequisitos() {
        System.out.println("\n--- MOSTRAR PRERREQUISITOS ---");

        System.out.print("Codigo de la materia: ");
        String codigo = teclado.nextLine();

        Materia materia = universidad.buscarMateria(codigo);

        if (materia == null) {
            System.out.println("Materia no encontrada.");
            return;
        }

        System.out.println("Materia: " + materia.getNombre());
        materia.mostrarPrerrequisitos();
    }

    // 8. INSCRIBIR ESTUDIANTE
    public void inscribirEstudiante() {
        System.out.println("\n--- INSCRIBIR ESTUDIANTE ---");

        System.out.print("ID del estudiante: ");
        String id = teclado.nextLine();

        System.out.print("Codigo de materia: ");
        String codigo = teclado.nextLine();

        Estudiante estudiante = universidad.buscarEstudiante(id);
        Materia materia = universidad.buscarMateria(codigo);

        if (estudiante == null || materia == null) {
            System.out.println("Estudiante o materia no encontrados.");
            return;
        }

        if (!materia.cumplePrerrequisitos(estudiante)) {
            System.out.println("El estudiante no ha aprobado los prerrequisitos.");
            return;
        }

        if (materia.inscribirEstudiante(estudiante)) {

            Operacion operacion = new Operacion("INSCRIBIR");

            operacion.estudiante = estudiante;
            operacion.materia = materia;

            guardarOperacion(operacion);

            System.out.println("Inscripcion exitosa.");

        } else if (materia.estaEnEspera(estudiante)) {
            System.out.println("Estudiante agregado a la cola de espera.");
        } else {
            System.out.println("El estudiante ya esta inscrito o en espera.");
        }
    }

    // 9. CANCELAR INSCRIPCION
    public void cancelarInscripcion() {
        System.out.println("\n--- CANCELAR INSCRIPCION ---");

        System.out.print("ID del estudiante: ");
        String id = teclado.nextLine();

        System.out.print("Codigo de materia: ");
        String codigo = teclado.nextLine();

        Estudiante estudiante = universidad.buscarEstudiante(id);
        Materia materia = universidad.buscarMateria(codigo);

        if (estudiante == null || materia == null) {
            System.out.println("Estudiante o materia no encontrados.");
            return;
        }

        if (!materia.estaInscrito(estudiante)) {
            System.out.println("El estudiante no esta inscrito.");
            return;
        }

        materia.cancelarInscripcion(estudiante);

        Operacion operacion =
                new Operacion("CANCELAR_INSCRIPCION");

        operacion.estudiante = estudiante;
        operacion.materia = materia;

        guardarOperacion(operacion);

        System.out.println("Cancelacion exitosa. Cupo liberado.");
    }

    // 10. MOSTRAR COLA DE ESPERA
    public void mostrarColaEspera() {
        System.out.println("\n--- MOSTRAR COLA DE ESPERA ---");

        System.out.print("Codigo de materia: ");
        String codigo = teclado.nextLine();

        Materia materia = universidad.buscarMateria(codigo);

        if (materia == null) {
            System.out.println("Materia no encontrada.");
            return;
        }

        System.out.println("Materia: " + materia.getNombre());
        materia.mostrarColaEspera();
    }

    // 11. RESERVAR HORARIO
    public void reservarHorario() {
        System.out.println("\n--- RESERVAR HORARIO EN AULA ---");

        System.out.print("Codigo del aula: ");
        String codigo = teclado.nextLine();

        Aula aula = universidad.buscarAula(codigo);

        if (aula == null) {
            System.out.println("Aula no encontrada.");
            return;
        }

        System.out.print("Dia (1=Lunes, 7=Domingo): ");
        int dia = leerEntero() - 1;

        System.out.print("Hora (0 a 23): ");
        int hora = leerEntero();

        System.out.print("Duracion en horas: ");
        int duracion = leerEntero();

        if (aula.reservar(dia, hora, duracion)) {

            Operacion operacion =
                    new Operacion("RESERVAR_AULA");

            operacion.aula = aula;
            operacion.dia = dia;
            operacion.hora = hora;
            operacion.duracion = duracion;

            guardarOperacion(operacion);

            System.out.println("Reserva exitosa.");
        } else {
            System.out.println("Horario conflictivo o datos invalidos.");
        }
    }

    // 12. LIBERAR HORARIO
    public void liberarHorario() {
        System.out.println("\n--- LIBERAR HORARIO ---");

        System.out.print("Codigo del aula: ");
        String codigo = teclado.nextLine();

        Aula aula = universidad.buscarAula(codigo);

        if (aula == null) {
            System.out.println("Aula no encontrada.");
            return;
        }

        System.out.print("Dia (1=Lunes, 7=Domingo): ");
        int dia = leerEntero() - 1;

        System.out.print("Hora (0 a 23): ");
        int hora = leerEntero();

        System.out.print("Duracion en horas: ");
        int duracion = leerEntero();

        if (aula.liberar(dia, hora, duracion)) {

            Operacion operacion =
                    new Operacion("LIBERAR_AULA");

            operacion.aula = aula;
            operacion.dia = dia;
            operacion.hora = hora;
            operacion.duracion = duracion;

            guardarOperacion(operacion);

            System.out.println("Horario liberado exitosamente.");
        } else {
            System.out.println("No se pudo liberar el horario.");
        }
    }

    // 13. CONSULTAR DISPONIBILIDAD
    public void consultarDisponibilidad() {
        System.out.println("\n--- CONSULTAR DISPONIBILIDAD ---");

        System.out.print("Codigo del aula: ");
        String codigo = teclado.nextLine();

        Aula aula = universidad.buscarAula(codigo);

        if (aula == null) {
            System.out.println("Aula no encontrada.");
            return;
        }

        System.out.print("Dia (1=Lunes, 7=Domingo): ");
        int dia = leerEntero() - 1;

        System.out.print("Hora (0 a 23): ");
        int hora = leerEntero();

        if (dia < 0 || dia > 6 || hora < 0 || hora > 23) {
            System.out.println("Dia u hora invalidos.");
            return;
        }

        if (aula.consultarDisponibilidad(dia, hora)) {
            System.out.println("Horario LIBRE.");
        } else {
            System.out.println("Horario RESERVADO.");
        }
    }

    // 14. CONEXIONES ENTRE EDIFICIOS
    public void registrarConexion() {
        System.out.println("\n--- AGREGAR CONEXION ENTRE EDIFICIOS ---");

        universidad.mostrarEdificios();

        System.out.print("Indice del edificio de origen: ");
        int origen = leerEntero();

        System.out.print("Indice del edificio de destino: ");
        int destino = leerEntero();

        System.out.print("Distancia en metros: ");
        int metros = leerEntero();

        if (origen < 0 || destino < 0 ||
            origen >= universidad.cantidadEdificios() ||
            destino >= universidad.cantidadEdificios() ||
            origen == destino || metros <= 0) {

            System.out.println("Datos de conexion invalidos.");
            return;
        }

        int anterior = universidad.obtenerDistancia(origen, destino);

        if (universidad.registrarDistancia(origen, destino, metros)) {

            if (anterior >= 0) {

                Operacion operacion =
                        new Operacion("CAMBIAR_DISTANCIA");

                operacion.origen = origen;
                operacion.destino = destino;
                operacion.distanciaAnterior = anterior;
                operacion.distanciaNueva = metros;

                guardarOperacion(operacion);
            }

            System.out.println("Conexion registrada exitosamente.");
        } else {
            System.out.println("No se pudo registrar la conexion.");
        }
    }

    // 15. DIJKSTRA
    public void calcularRuta() {
        System.out.println("\n--- CALCULAR RUTA MAS CORTA ---");

        int cantidad = universidad.cantidadEdificios();

        if (cantidad == 0) {
            System.out.println("No hay edificios registrados.");
            return;
        }

        universidad.mostrarEdificios();

        System.out.print("Edificio de origen (indice): ");
        int origen = leerEntero();

        System.out.print("Edificio de destino (indice): ");
        int destino = leerEntero();

        if (origen < 0 || destino < 0 ||
            origen >= cantidad || destino >= cantidad) {
            System.out.println("Edificio no valido.");
            return;
        }

        int[] distanciaMinima = new int[cantidad];
        boolean[] visitado = new boolean[cantidad];
        int[] anterior = new int[cantidad];

        int[][] distancias = universidad.getDistancias();

        for (int i = 0; i < cantidad; i++) {
            distanciaMinima[i] = Integer.MAX_VALUE;
            anterior[i] = -1;
        }

        distanciaMinima[origen] = 0;

        for (int i = 0; i < cantidad; i++) {
            int actual = -1;
            int menor = Integer.MAX_VALUE;

            for (int j = 0; j < cantidad; j++) {
                if (!visitado[j] && distanciaMinima[j] < menor) {
                    menor = distanciaMinima[j];
                    actual = j;
                }
            }

            if (actual == -1) {
                break;
            }

            visitado[actual] = true;

            for (int j = 0; j < cantidad; j++) {
                int peso = distancias[actual][j];

                if (peso >= 0 && !visitado[j] &&
                    distanciaMinima[actual] != Integer.MAX_VALUE &&
                    distanciaMinima[actual] + peso < distanciaMinima[j]) {

                    distanciaMinima[j] = distanciaMinima[actual] + peso;
                    anterior[j] = actual;
                }
            }
        }

        if (distanciaMinima[destino] == Integer.MAX_VALUE) {
            System.out.println("No existe una ruta.");
            return;
        }

        Stack<Integer> ruta = new Stack<>();
        int actual = destino;

        while (actual != -1) {
            ruta.push(actual);
            actual = anterior[actual];
        }

        System.out.println("\n--- RESULTADO ---");
        System.out.println("Ruta mas corta:");

        while (!ruta.isEmpty()) {
            int indice = ruta.pop();

            System.out.print(
                universidad.buscarEdificio(indice).getNombre()
            );

            if (!ruta.isEmpty()) {
                System.out.print(" -> ");
            }
        }

        System.out.println();
        System.out.println("Distancia TOTAL: " +
                distanciaMinima[destino] + " metros");
    }

    // 16. REGISTRAR NOTA
    public void registrarNota() {
        System.out.println("\n--- REGISTRAR NOTA ---");

        System.out.print("ID del estudiante: ");
        String id = teclado.nextLine();

        Estudiante estudiante = universidad.buscarEstudiante(id);

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        System.out.print("Semestre (1 a 10): ");
        int semestre = leerEntero() - 1;

        System.out.print("Posicion de materia (1 a 20): ");
        int posicion = leerEntero() - 1;

        System.out.print("Nota (0 a 5): ");
        double nota = leerDecimal();

        if (semestre < 0 || semestre >= 10 ||
            posicion < 0 || posicion >= 20 ||
            nota < 0 || nota > 5) {
            System.out.println("Datos de nota invalidos.");
            return;
        }

        Double anterior = estudiante.consultarNota(semestre, posicion);

        estudiante.registrarNota(semestre, posicion, nota);

        Operacion operacion =
                new Operacion("REGISTRAR_NOTA");

        operacion.estudiante = estudiante;
        operacion.semestre = semestre;
        operacion.posicion = posicion;
        operacion.notaAnterior = anterior;
        operacion.notaNueva = nota;

        guardarOperacion(operacion);

        System.out.println("Nota registrada exitosamente.");
    }

    // PROMEDIO POR SEMESTRE
    public double calcularPromedioSemestre(String id, int semestre) {
        Estudiante estudiante = universidad.buscarEstudiante(id);

        if (estudiante == null) {
            return 0;
        }

        Double promedio = estudiante.calcularPromedioSemestral(semestre);

        return promedio == null ? 0 : promedio;
    }

    // PROMEDIO ACUMULADO
    public double calcularPromedioAcumulado(String id) {
        Estudiante estudiante = universidad.buscarEstudiante(id);

        if (estudiante == null) {
            return 0;
        }

        Double promedio = estudiante.calcularPromedioAcumulado();

        return promedio == null ? 0 : promedio;
    }

    // 17. REPORTE ACADEMICO
    public void mostrarReporte(String id) {
        Estudiante estudiante = universidad.buscarEstudiante(id);

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        System.out.println("\n--- REPORTE ACADEMICO ---");
        System.out.println("Estudiante: " + estudiante.getNombre());
        System.out.println("ID: " + estudiante.getId());

        for (int i = 0; i < 10; i++) {
            Double promedio = estudiante.calcularPromedioSemestral(i);

            if (promedio != null) {
                System.out.println("\nSemestre " + (i + 1));

                for (int j = 0; j < 20; j++) {
                    Double nota = estudiante.consultarNota(i, j);

                    if (nota != null) {
                        String estado = nota >= 3.0
                                ? "APROBADA" : "REPROBADA";

                        System.out.println(
                            "Materia " + (j + 1) +
                            ": " + nota + " - " + estado
                        );
                    }
                }

                System.out.printf(
                    "Promedio semestral: %.2f%n",
                    promedio
                );

                System.out.println(
                    "Aprobadas: " +
                    estudiante.contarMateriasAprobadas(i)
                );

                System.out.println(
                    "Reprobadas: " +
                    estudiante.contarMateriasReprobadas(i)
                );
            }
        }

        Double acumulado = estudiante.calcularPromedioAcumulado();

        System.out.println("\n=== RESUMEN ACUMULADO ===");

        if (acumulado == null) {
            System.out.println("No hay notas registradas.");
        } else {
            System.out.printf("Promedio acumulado: %.2f%n", acumulado);
        }

        System.out.println(
            "Materias aprobadas: " +
            estudiante.contarMateriasAprobadasAcumuladas()
        );

        System.out.println(
            "Materias reprobadas: " +
            estudiante.contarMateriasReprobadasAcumuladas()
        );
    }

    public void abrirReporte() {
        System.out.print("ID del estudiante: ");
        String id = teclado.nextLine();

        if (universidad.buscarEstudiante(id) == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        if (!reporteActual.isEmpty()) {
            pilaReportesAtras.push(reporteActual);
        }

        reporteActual = id;
        pilaReportesAdelante.clear();

        mostrarReporte(id);
    }

    // 18. NAVEGADOR DE REPORTES
    public void navegarReportes() {
        System.out.println("\n--- NAVEGADOR DE REPORTES ---");
        System.out.println("1. Reporte anterior");
        System.out.println("2. Reporte siguiente");
        System.out.print("Seleccione: ");

        int opcion = leerEntero();

        if (opcion == 1) {
            regresarReporte();
        } else if (opcion == 2) {
            avanzarReporte();
        } else {
            System.out.println("Opcion no valida.");
        }
    }

    public void regresarReporte() {
        if (pilaReportesAtras.isEmpty()) {
            System.out.println("No hay reportes anteriores.");
            return;
        }

        pilaReportesAdelante.push(reporteActual);
        reporteActual = pilaReportesAtras.pop();

        mostrarReporte(reporteActual);
    }

    public void avanzarReporte() {
        if (pilaReportesAdelante.isEmpty()) {
            System.out.println("No hay reportes siguientes.");
            return;
        }

        pilaReportesAtras.push(reporteActual);
        reporteActual = pilaReportesAdelante.pop();

        mostrarReporte(reporteActual);
    }

    // 19. DESHACER
    public void deshacerOperacion() {
        System.out.println("\n--- DESHACER ---");

        if (pilaDeshacer.isEmpty()) {
            System.out.println("No hay operaciones para deshacer.");
            return;
        }

        Operacion operacion = pilaDeshacer.pop();

        if (operacion.tipo.equals("REGISTRAR_ESTUDIANTE")) {
            universidad.eliminarEstudiante(
                operacion.estudiante.getId()
            );

        } else if (operacion.tipo.equals("ELIMINAR_ESTUDIANTE")) {
            universidad.registrarEstudiante(
                operacion.estudiante
            );

        } else if (operacion.tipo.equals("REGISTRAR_MATERIA")) {
            universidad.eliminarMateria(
                operacion.codigoMateria
            );

        } else if (operacion.tipo.equals("INSCRIBIR")) {
            operacion.materia.cancelarInscripcion(
                operacion.estudiante
            );

        } else if (operacion.tipo.equals("CANCELAR_INSCRIPCION")) {
            operacion.materia.inscribirEstudiante(
                operacion.estudiante
            );

        } else if (operacion.tipo.equals("RESERVAR_AULA")) {
            operacion.aula.liberar(
                operacion.dia,
                operacion.hora,
                operacion.duracion
            );

        } else if (operacion.tipo.equals("LIBERAR_AULA")) {
            operacion.aula.reservar(
                operacion.dia,
                operacion.hora,
                operacion.duracion
            );

        } else if (operacion.tipo.equals("REGISTRAR_NOTA")) {
            operacion.estudiante.getNotas()
                [operacion.semestre][operacion.posicion] =
                    operacion.notaAnterior;

        } else if (operacion.tipo.equals("CAMBIAR_DISTANCIA")) {
            universidad.registrarDistancia(
                operacion.origen,
                operacion.destino,
                operacion.distanciaAnterior
            );
        }

        pilaRehacer.push(operacion);

        System.out.println("Operacion deshecha.");
    }

    // 20. REHACER
    public void rehacerOperacion() {
        System.out.println("\n--- REHACER ---");

        if (pilaRehacer.isEmpty()) {
            System.out.println("No hay operaciones para rehacer.");
            return;
        }

        Operacion operacion = pilaRehacer.pop();

        if (operacion.tipo.equals("REGISTRAR_ESTUDIANTE")) {
            universidad.registrarEstudiante(
                operacion.estudiante
            );

        } else if (operacion.tipo.equals("ELIMINAR_ESTUDIANTE")) {
            universidad.eliminarEstudiante(
                operacion.estudiante.getId()
            );

        } else if (operacion.tipo.equals("REGISTRAR_MATERIA")) {
            universidad.registrarMateria(
                operacion.materia
            );

        } else if (operacion.tipo.equals("INSCRIBIR")) {
            operacion.materia.inscribirEstudiante(
                operacion.estudiante
            );

        } else if (operacion.tipo.equals("CANCELAR_INSCRIPCION")) {
            operacion.materia.cancelarInscripcion(
                operacion.estudiante
            );

        } else if (operacion.tipo.equals("RESERVAR_AULA")) {
            operacion.aula.reservar(
                operacion.dia,
                operacion.hora,
                operacion.duracion
            );

        } else if (operacion.tipo.equals("LIBERAR_AULA")) {
            operacion.aula.liberar(
                operacion.dia,
                operacion.hora,
                operacion.duracion
            );

        } else if (operacion.tipo.equals("REGISTRAR_NOTA")) {
            operacion.estudiante.registrarNota(
                operacion.semestre,
                operacion.posicion,
                operacion.notaNueva
            );

        } else if (operacion.tipo.equals("CAMBIAR_DISTANCIA")) {
            universidad.registrarDistancia(
                operacion.origen,
                operacion.destino,
                operacion.distanciaNueva
            );
        }

        pilaDeshacer.push(operacion);

        System.out.println("Operacion rehecha.");
    }

    // 21. ARCHIVO CSV
    public void cargarArchivoCSV() {
        System.out.println("\n--- PROCESAMIENTO POR LOTES ---");

        System.out.print("Nombre o ruta del archivo CSV: ");
        String nombreArchivo = teclado.nextLine();

        colaSolicitudes.clear();

        try (BufferedReader lector =
                Files.newBufferedReader(Path.of(nombreArchivo))) {

            String linea = lector.readLine();

            while ((linea = lector.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    colaSolicitudes.offer(linea);
                }
            }

            System.out.println("Se encolaron " +
                    colaSolicitudes.size() + " solicitudes.");

        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo: " +
                    e.getMessage());
        }
    }

    public void procesarSolicitudesBatch() {
        int exitosas = 0;
        int enEspera = 0;
        int fallidas = 0;

        int total = colaSolicitudes.size();
        int numero = 0;

        System.out.println("\nProcesando cola...");

        while (!colaSolicitudes.isEmpty()) {
            numero++;

            String solicitud = colaSolicitudes.poll();
            String[] datos = solicitud.split(",");

            if (datos.length != 2) {
                fallidas++;
                System.out.println("Solicitud con formato invalido.");
                continue;
            }

            String id = datos[0].trim();
            String codigo = datos[1].trim();

            Estudiante estudiante = universidad.buscarEstudiante(id);
            Materia materia = universidad.buscarMateria(codigo);

            if (estudiante == null || materia == null) {
                fallidas++;
                System.out.println("[" + numero + "/" + total +
                        "] " + id + " -> " + codigo + " -> Fallida");
                continue;
            }

            if (!materia.cumplePrerrequisitos(estudiante)) {
                fallidas++;
                System.out.println("[" + numero + "/" + total +
                        "] " + id + " -> " + codigo +
                        " -> Fallida: prerrequisito");
                continue;
            }

            boolean inscrito = materia.inscribirEstudiante(estudiante);

            if (inscrito) {
                exitosas++;
                System.out.println("[" + numero + "/" + total +
                        "] " + id + " -> " + codigo + " -> Exitosa");
            } else if (materia.estaEnEspera(estudiante)) {
                enEspera++;
                System.out.println("[" + numero + "/" + total +
                        "] " + id + " -> " + codigo + " -> En espera");
            } else {
                fallidas++;
                System.out.println("[" + numero + "/" + total +
                        "] " + id + " -> " + codigo + " -> Fallida");
            }
        }

        System.out.println("\n=== RESUMEN ===");
        System.out.println("Exitosas: " + exitosas);
        System.out.println("En lista de espera: " + enEspera);
        System.out.println("Fallidas: " + fallidas);
    }

    // LECTURA DE NUMEROS
    private int leerEntero() {
        while (!teclado.hasNextInt()) {
            System.out.print("Ingrese un numero entero valido: ");
            teclado.nextLine();
        }

        int valor = teclado.nextInt();
        teclado.nextLine();

        return valor;
    }

    private double leerDecimal() {
        while (!teclado.hasNextDouble()) {
            System.out.print("Ingrese un numero valido: ");
            teclado.nextLine();
        }

        double valor = teclado.nextDouble();
        teclado.nextLine();

        return valor;
    }

    // MENU PRINCIPAL
    public void mostrarMenu() {
        int opcion = 0;

        while (opcion != 22) {
            System.out.println("\n============================================================");
            System.out.println("       PLANIFICACION ACADEMICA - SISTEMA UNIVERSITARIO");
            System.out.println("============================================================");

            System.out.println("\n=== GESTION DE ESTUDIANTES ===");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Buscar estudiante por ID");
            System.out.println("3. Listar todos los estudiantes");
            System.out.println("4. Eliminar estudiante");

            System.out.println("\n=== GESTION DE MATERIAS ===");
            System.out.println("5. Crear materia");
            System.out.println("6. Agregar pre-requisito");
            System.out.println("7. Mostrar pre-requisitos");
            System.out.println("8. Inscribir estudiante");
            System.out.println("9. Cancelar inscripcion");
            System.out.println("10. Mostrar cola de espera");

            System.out.println("\n=== GESTION DE HORARIOS ===");
            System.out.println("11. Reservar horario en aula");
            System.out.println("12. Liberar horario");
            System.out.println("13. Consultar disponibilidad");

            System.out.println("\n=== RUTAS ENTRE EDIFICIOS ===");
            System.out.println("14. Agregar conexion entre edificios");
            System.out.println("15. Calcular ruta mas corta");

            System.out.println("\n=== REPORTES ACADEMICOS ===");
            System.out.println("16. Registrar nota");
            System.out.println("17. Ver reporte academico");
            System.out.println("18. Navegador de reportes (atras/adelante)");

            System.out.println("\n=== SISTEMA DESHACER/REHACER ===");
            System.out.println("19. Deshacer ultima operacion");
            System.out.println("20. Rehacer ultima operacion");

            System.out.println("\n=== PROCESAMIENTO POR LOTES ===");
            System.out.println("21. Procesar archivo CSV");

            System.out.println("\n22. Salir");

            System.out.print("\nSeleccione una opcion: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarEstudiante();
                    break;
                case 2:
                    buscarEstudiante();
                    break;
                case 3:
                    listarEstudiantes();
                    break;
                case 4:
                    eliminarEstudiante();
                    break;
                case 5:
                    registrarMateria();
                    break;
                case 6:
                    agregarPrerrequisito();
                    break;
                case 7:
                    mostrarPrerrequisitos();
                    break;
                case 8:
                    inscribirEstudiante();
                    break;
                case 9:
                    cancelarInscripcion();
                    break;
                case 10:
                    mostrarColaEspera();
                    break;
                case 11:
                    reservarHorario();
                    break;
                case 12:
                    liberarHorario();
                    break;
                case 13:
                    consultarDisponibilidad();
                    break;
                case 14:
                    registrarConexion();
                    break;
                case 15:
                    calcularRuta();
                    break;
                case 16:
                    registrarNota();
                    break;
                case 17:
                    abrirReporte();
                    break;
                case 18:
                    navegarReportes();
                    break;
                case 19:
                    deshacerOperacion();
                    break;
                case 20:
                    rehacerOperacion();
                    break;
                case 21:
                    cargarArchivoCSV();
                    procesarSolicitudesBatch();
                    break;
                case 22:
                    System.out.println("Cerrando sistema...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }
}