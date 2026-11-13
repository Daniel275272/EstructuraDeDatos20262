
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

    //Estudiantes iniciales

    public void cargarEstudiantesIniciales() {

        Estudiante e1 = new Estudiante("Daniel", "1001", "daniel@gmail.com", 3);
        Estudiante e2 = new Estudiante("Carlos", "1002", "carlos@gmail.com", 2);
        Estudiante e3 = new Estudiante("Laura", "1003", "laura@gmail.com", 4);
        Estudiante e4 = new Estudiante("Andres", "1004", "andres@gmail.com", 1);
        Estudiante e5 = new Estudiante("Sofia", "1005", "sofia@gmail.com", 3);

        universidad.registrarEstudiante(e1);
        universidad.registrarEstudiante(e2);
        universidad.registrarEstudiante(e3);
        universidad.registrarEstudiante(e4);
        universidad.registrarEstudiante(e5);
    }
    // MATERIAS INICIALES

    public void cargarMateriasIniciales() {
         Materia m1 = new Materia("MAT01", "Matematicas Basicas", 30, 3);
         Materia m2 = new Materia("PRO01", "Fundamentos de Programacion", 25, 4);
         Materia m3 = new Materia("BD01", "Bases de Datos", 20, 3);
         Materia m4 = new Materia("ED01", "Estructuras de Datos", 20, 4);
         Materia m5 = new Materia("ARQ01", "Arquitectura de Computadores", 25, 3);

         universidad.registrarMateria(m1);
         universidad.registrarMateria(m2);
         universidad.registrarMateria(m3);
         universidad.registrarMateria(m4);
         universidad.registrarMateria(m5);

    }

    // REGISTRO DE OPERACIONES PARA DESHACER Y REHACER

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

        int origen;
        int destino;
        int distanciaAnterior;
        int distanciaNueva;

        Double notaAnterior;
        double notaNueva;

        boolean estabaEnEspera;

        Operacion(String tipo) {
            this.tipo = tipo;
        }
    }

    private void guardarOperacion(Operacion operacion) {
        pilaDeshacer.push(operacion);
        pilaRehacer.clear();
    }

    // INICIO DEL SISTEMA

    public static void main(String[] args) {
        GestorSistema sistema = new GestorSistema();
        sistema.ejecutar();
    }

    public void ejecutar() {
        cargarEstudiantesIniciales();
        cargarMateriasIniciales();
        int opcion;

        do {
            mostrarMenu();
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
                    mostrarReporte();
                    break;

                case 18:
                    abrirReporte();
                    break;

                case 19:
                    navegarReportes();
                    break;

                case 20:
                    deshacerOperacion();
                    break;

                case 21:
                    rehacerOperacion();
                    break;

                case 22:
                    cargarArchivoCSV();
                    procesarSolicitudesBatch();
                    break;

                case 0:
                    System.out.println("Cerrando el sistema...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
                    break;
            }

        } while (opcion != 0);
    }

    // MENU PRINCIPAL

    public void mostrarMenu() {

        System.out.println("\n====================================");
        System.out.println("     SISTEMA DE GESTION UNIVERSITARIA");
        System.out.println("====================================");

        System.out.println("1. Registrar estudiante");
        System.out.println("2. Buscar estudiante");
        System.out.println("3. Listar estudiantes");
        System.out.println("4. Eliminar estudiante");

        System.out.println("\n--- MATERIAS ---");
        System.out.println("5. Registrar materia");
        System.out.println("6. Agregar prerrequisito");
        System.out.println("7. Mostrar prerrequisitos");
        System.out.println("8. Inscribir estudiante");
        System.out.println("9. Cancelar inscripcion");
        System.out.println("10. Mostrar cola de espera");

        System.out.println("\n--- AULAS ---");
        System.out.println("11. Reservar horario");
        System.out.println("12. Liberar horario");
        System.out.println("13. Consultar disponibilidad");

        System.out.println("\n--- RUTAS DEL CAMPUS ---");
        System.out.println("14. Registrar conexion");
        System.out.println("15. Calcular ruta");

        System.out.println("\n--- CALIFICACIONES ---");
        System.out.println("16. Registrar nota");
        System.out.println("17. Mostrar reporte");

        System.out.println("\n--- HISTORIAL DE REPORTES ---");
        System.out.println("18. Abrir reporte");
        System.out.println("19. Navegar reportes");

        System.out.println("\n--- PILAS ---");
        System.out.println("20. Deshacer operacion");
        System.out.println("21. Rehacer operacion");

        System.out.println("\n--- PROCESAMIENTO POR LOTES ---");
        System.out.println("22. Cargar y procesar archivo CSV");

        System.out.println("\n0. Salir");
        System.out.print("\nSeleccione una opcion: ");
    }

    // ESTUDIANTES

    private void registrarEstudiante() {

        System.out.println("\n--- REGISTRAR ESTUDIANTE ---");

        System.out.print("ID: ");
        String id = teclado.nextLine();

        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();

        System.out.print("Email: ");
        String email = teclado.nextLine();

        System.out.print("Semestre (0-9): ");
        int semestre = leerEntero();

        if (semestre < 0 || semestre >= 10) {
            System.out.println("Semestre no valido.");
            return;
        }

        Estudiante estudiante =
                new Estudiante(nombre, id, email, semestre);

        if (universidad.registrarEstudiante(estudiante)) {

            Operacion operacion = new Operacion("REGISTRAR_ESTUDIANTE");
            operacion.estudiante = estudiante;

            guardarOperacion(operacion);

            System.out.println("Estudiante registrado correctamente.");

        } else {
            System.out.println("No fue posible registrar el estudiante.");
        }
    }

    private void buscarEstudiante() {

        System.out.println("\n--- BUSCAR ESTUDIANTE ---");

        System.out.print("Ingrese el ID: ");
        String id = teclado.nextLine();

        Estudiante estudiante = universidad.buscarEstudiante(id);

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        estudiante.mostrarInformacion();

        Double promedio = estudiante.calcularPromedioAcumulado();

        if (promedio == null) {
            System.out.println("Promedio acumulado: Sin notas.");
        } else {
            System.out.println("Promedio acumulado: " + promedio);
        }
    }

    private void listarEstudiantes() {

        System.out.println("\n--- LISTA DE ESTUDIANTES ---");

        universidad.mostrarEstudiantes();
    }

    private void eliminarEstudiante() {

        System.out.println("\n--- ELIMINAR ESTUDIANTE ---");

        System.out.print("Ingrese el ID: ");
        String id = teclado.nextLine();

        Estudiante estudiante = universidad.buscarEstudiante(id);

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        if (universidad.eliminarEstudiante(id)) {

            Operacion operacion = new Operacion("ELIMINAR_ESTUDIANTE");
            operacion.estudiante = estudiante;

            guardarOperacion(operacion);

            System.out.println("Estudiante eliminado correctamente.");

        } else {
            System.out.println("No fue posible eliminar el estudiante.");
        }
    }

    // MATERIAS

    private void registrarMateria() {

        System.out.println("\n--- REGISTRAR MATERIA ---");

        System.out.print("Codigo: ");
        String codigo = teclado.nextLine();

        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();

        System.out.print("Cupos maximos: ");
        int cupos = leerEntero();

        System.out.print("Creditos: ");
        int creditos = leerEntero();

        if (cupos <= 0 || creditos <= 0) {
            System.out.println("Los cupos y creditos deben ser positivos.");
            return;
        }

        Materia materia =
                new Materia(codigo, nombre, cupos, creditos);

        if (universidad.registrarMateria(materia)) {

            Operacion operacion = new Operacion("REGISTRAR_MATERIA");
            operacion.materia = materia;
            operacion.codigoMateria = codigo;

            guardarOperacion(operacion);

            System.out.println("Materia registrada correctamente.");

        } else {
            System.out.println("No fue posible registrar la materia.");
        }
    }

    private void agregarPrerrequisito() {

        System.out.println("\n--- AGREGAR PRERREQUISITO ---");

        System.out.print("Codigo de la materia principal: ");
        String codigoPrincipal = teclado.nextLine();

        System.out.print("Codigo del prerrequisito: ");
        String codigoRequisito = teclado.nextLine();

        Materia principal =
                universidad.buscarMateria(codigoPrincipal);

        Materia requisito =
                universidad.buscarMateria(codigoRequisito);

        if (principal == null || requisito == null) {
            System.out.println("Una de las materias no existe.");
            return;
        }

        if (principal.agregarPrerrequisito(requisito)) {
            System.out.println("Prerrequisito agregado correctamente.");
        } else {
            System.out.println("No fue posible agregar el prerrequisito.");
        }
    }

    private void mostrarPrerrequisitos() {

        System.out.println("\n--- CONSULTAR PRERREQUISITOS ---");

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

    private void inscribirEstudiante() {

        System.out.println("\n--- INSCRIBIR ESTUDIANTE ---");

        System.out.print("ID del estudiante: ");
        String id = teclado.nextLine();

        System.out.print("Codigo de la materia: ");
        String codigo = teclado.nextLine();

        Estudiante estudiante = universidad.buscarEstudiante(id);
        Materia materia = universidad.buscarMateria(codigo);

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        if (materia == null) {
            System.out.println("Materia no encontrada.");
            return;
        }

        if (!materia.cumplePrerrequisitos(estudiante)) {
            System.out.println("El estudiante no cumple los prerrequisitos.");
            return;
        }

        boolean inscrito = materia.inscribirEstudiante(estudiante);

        if (inscrito) {

            Operacion operacion = new Operacion("INSCRIBIR");
            operacion.estudiante = estudiante;
            operacion.materia = materia;
            operacion.estabaEnEspera = false;

            guardarOperacion(operacion);

            System.out.println("Inscripcion realizada correctamente.");

        } else if (materia.estaEnEspera(estudiante)) {

            Operacion operacion = new Operacion("INSCRIBIR");
            operacion.estudiante = estudiante;
            operacion.materia = materia;
            operacion.estabaEnEspera = true;

            guardarOperacion(operacion);

            System.out.println("No hay cupos. Estudiante agregado a la lista de espera.");

        } else {
            System.out.println("No fue posible realizar la inscripcion.");
        }
    }

    private void cancelarInscripcion() {

        System.out.println("\n--- CANCELAR INSCRIPCION ---");

        System.out.print("ID del estudiante: ");
        String id = teclado.nextLine();

        System.out.print("Codigo de la materia: ");
        String codigo = teclado.nextLine();

        Estudiante estudiante = universidad.buscarEstudiante(id);
        Materia materia = universidad.buscarMateria(codigo);

        if (estudiante == null || materia == null) {
            System.out.println("Estudiante o materia no encontrados.");
            return;
        }

        if (!materia.estaInscrito(estudiante)) {
            System.out.println("El estudiante no esta inscrito en esta materia.");
            return;
        }

        if (materia.cancelarInscripcion(estudiante)) {

            Operacion operacion = new Operacion("CANCELAR_INSCRIPCION");
            operacion.estudiante = estudiante;
            operacion.materia = materia;

            guardarOperacion(operacion);

            System.out.println("Inscripcion cancelada correctamente.");

        } else {
            System.out.println("No fue posible cancelar la inscripcion.");
        }
    }

    private void mostrarColaEspera() {

        System.out.println("\n--- COLA DE ESPERA ---");

        System.out.print("Codigo de la materia: ");
        String codigo = teclado.nextLine();

        Materia materia = universidad.buscarMateria(codigo);

        if (materia == null) {
            System.out.println("Materia no encontrada.");
            return;
        }

        materia.mostrarColaEspera();

        System.out.println("Estudiantes en espera: " +
                materia.cantidadEnEspera());
    }

    // AULAS

    private void reservarHorario() {

        System.out.println("\n--- RESERVAR AULA ---");

        System.out.print("Codigo del aula: ");
        String codigo = teclado.nextLine();

        Aula aula = universidad.buscarAula(codigo);

        if (aula == null) {
            System.out.println("Aula no encontrada.");
            return;
        }

        System.out.print("Dia (0-6): ");
        int dia = leerEntero();

        System.out.print("Hora de inicio (0-23): ");
        int hora = leerEntero();

        System.out.print("Duracion en horas: ");
        int duracion = leerEntero();

        if (aula.reservar(dia, hora, duracion)) {

            Operacion operacion = new Operacion("RESERVAR_AULA");
            operacion.aula = aula;
            operacion.dia = dia;
            operacion.hora = hora;
            operacion.duracion = duracion;

            guardarOperacion(operacion);

            System.out.println("Aula reservada correctamente.");

        } else {
            System.out.println("No fue posible reservar el horario.");
        }
    }

    private void liberarHorario() {

        System.out.println("\n--- LIBERAR AULA ---");

        System.out.print("Codigo del aula: ");
        String codigo = teclado.nextLine();

        Aula aula = universidad.buscarAula(codigo);

        if (aula == null) {
            System.out.println("Aula no encontrada.");
            return;
        }

        System.out.print("Dia (0-6): ");
        int dia = leerEntero();

        System.out.print("Hora de inicio (0-23): ");
        int hora = leerEntero();

        System.out.print("Duracion en horas: ");
        int duracion = leerEntero();

        if (aula.liberar(dia, hora, duracion)) {

            Operacion operacion = new Operacion("LIBERAR_AULA");
            operacion.aula = aula;
            operacion.dia = dia;
            operacion.hora = hora;
            operacion.duracion = duracion;

            guardarOperacion(operacion);

            System.out.println("Horario liberado correctamente.");

        } else {
            System.out.println("No fue posible liberar el horario.");
        }
    }

    private void consultarDisponibilidad() {

        System.out.println("\n--- CONSULTAR DISPONIBILIDAD ---");

        System.out.print("Codigo del aula: ");
        String codigo = teclado.nextLine();

        Aula aula = universidad.buscarAula(codigo);

        if (aula == null) {
            System.out.println("Aula no encontrada.");
            return;
        }

        System.out.print("Dia (0-6): ");
        int dia = leerEntero();

        System.out.print("Hora (0-23): ");
        int hora = leerEntero();

        if (aula.consultarDisponibilidad(dia, hora)) {
            System.out.println("El aula esta disponible.");
        } else {
            System.out.println("El aula no esta disponible o los datos no son validos.");
        }
    }

    // CONEXIONES Y RUTAS DEL CAMPUS

    private void registrarConexion() {

        System.out.println("\n--- REGISTRAR CONEXION ---");

        universidad.mostrarEdificios();

        System.out.print("Indice del edificio de origen: ");
        int origen = leerEntero();

        System.out.print("Indice del edificio de destino: ");
        int destino = leerEntero();

        System.out.print("Distancia en metros: ");
        int distancia = leerEntero();

        int anterior = universidad.obtenerDistancia(origen, destino);

        if (universidad.registrarDistancia(origen, destino, distancia)) {

            Operacion operacion = new Operacion("CAMBIAR_DISTANCIA");

            operacion.origen = origen;
            operacion.destino = destino;
            operacion.distanciaAnterior = anterior;
            operacion.distanciaNueva = distancia;

            guardarOperacion(operacion);

            System.out.println("Conexion registrada correctamente.");

        } else {
            System.out.println("No fue posible registrar la conexion.");
        }
    }

    private void calcularRuta() {

        System.out.println("\n--- CALCULAR RUTA ---");

        universidad.mostrarEdificios();

        int cantidad = universidad.cantidadEdificios();

        if (cantidad == 0) {
            System.out.println("No hay edificios registrados.");
            return;
        }

        System.out.print("Edificio de origen: ");
        int origen = leerEntero();

        System.out.print("Edificio de destino: ");
        int destino = leerEntero();

        if (origen < 0 || origen >= cantidad ||
            destino < 0 || destino >= cantidad) {

            System.out.println("Indices no validos.");
            return;
        }

        int[][] distancias = universidad.getDistancias();

        int[] distanciaMinima = new int[cantidad];
        boolean[] visitado = new boolean[cantidad];
        int[] anterior = new int[cantidad];

        int infinito = 999999999;

        for (int i = 0; i < cantidad; i++) {
            distanciaMinima[i] = infinito;
            visitado[i] = false;
            anterior[i] = -1;
        }

        distanciaMinima[origen] = 0;

        // ALGORITMO DE DIJKSTRA

        for (int i = 0; i < cantidad; i++) {

            int menor = infinito;
            int actual = -1;

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

                if (distancias[actual][j] >= 0 &&
                    !visitado[j]) {

                    int nuevaDistancia =
                            distanciaMinima[actual] + distancias[actual][j];

                    if (nuevaDistancia < distanciaMinima[j]) {

                        distanciaMinima[j] = nuevaDistancia;
                        anterior[j] = actual;
                    }
                }
            }
        }

        if (distanciaMinima[destino] == infinito) {
            System.out.println("No existe una ruta entre los edificios.");
            return;
        }

        Stack<Integer> ruta = new Stack<>();

        int actual = destino;

        while (actual != -1) {
            ruta.push(actual);
            actual = anterior[actual];
        }

        System.out.println("\nRuta encontrada:");

        while (!ruta.isEmpty()) {

            int posicion = ruta.pop();

            Edificio edificio = universidad.buscarEdificio(posicion);

            System.out.print(edificio.getNombre());

            if (!ruta.isEmpty()) {
                System.out.print(" -> ");
            }
        }

        System.out.println("\nDistancia total: " +
                distanciaMinima[destino] + " metros");
    }

    // CALIFICACIONES

    private void registrarNota() {

        System.out.println("\n--- REGISTRAR NOTA ---");

        System.out.print("ID del estudiante: ");
        String id = teclado.nextLine();

        Estudiante estudiante = universidad.buscarEstudiante(id);

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        System.out.print("Semestre (0-9): ");
        int semestre = leerEntero();

        System.out.print("Posicion de la materia (0-19): ");
        int posicion = leerEntero();

        System.out.print("Nota (0.0-5.0): ");
        double nota = leerDecimal();

        if (semestre < 0 || semestre >= 10 ||
            posicion < 0 || posicion >= 20 ||
            nota < 0 || nota > 5) {

            System.out.println("Datos de nota no validos.");
            return;
        }

        Double anterior = estudiante.consultarNota(semestre, posicion);

        estudiante.registrarNota(semestre, posicion, nota);

        Operacion operacion = new Operacion("REGISTRAR_NOTA");

        operacion.estudiante = estudiante;
        operacion.semestre = semestre;
        operacion.posicion = posicion;
        operacion.notaAnterior = anterior;
        operacion.notaNueva = nota;

        guardarOperacion(operacion);

        System.out.println("Nota registrada correctamente.");
    }

    private Double calcularPromedioSemestre(
            Estudiante estudiante, int semestre) {

        Double promedio = estudiante.calcularPromedioSemestral(semestre);

        if (promedio == null) {
            return 0.0;
        }

        return promedio;
    }

    private Double calcularPromedioAcumulado(Estudiante estudiante) {

        Double promedio = estudiante.calcularPromedioAcumulado();

        if (promedio == null) {
            return 0.0;
        }

        return promedio;
    }

    private void mostrarReporte() {

        System.out.println("\n--- REPORTE ACADEMICO ---");

        System.out.print("ID del estudiante: ");
        String id = teclado.nextLine();

        Estudiante estudiante = universidad.buscarEstudiante(id);

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        estudiante.mostrarInformacion();

        System.out.println("\n--- NOTAS POR SEMESTRE ---");

        for (int semestre = 0; semestre < 10; semestre++) {

            System.out.println("\nSemestre " + semestre);

            int cantidadNotas = 0;

            for (int posicion = 0; posicion < 20; posicion++) {

                Double nota = estudiante.consultarNota(semestre, posicion);

                if (nota != null) {

                    String estado;

                    if (nota >= 3.0) {
                        estado = "APROBADA";
                    } else {
                        estado = "REPROBADA";
                    }

                    System.out.println(
                            "Materia " + posicion +
                            " | Nota: " + nota +
                            " | Estado: " + estado
                    );

                    cantidadNotas++;
                }
            }

            if (cantidadNotas == 0) {
                System.out.println("No hay notas registradas.");
            } else {
                System.out.println(
                        "Promedio del semestre: " +
                        calcularPromedioSemestre(estudiante, semestre)
                );
            }

            System.out.println(
                    "Materias aprobadas: " +
                    estudiante.contarMateriasAprobadas(semestre)
            );

            System.out.println(
                    "Materias reprobadas: " +
                    estudiante.contarMateriasReprobadas(semestre)
            );
        }

        System.out.println("\n--- RESUMEN ACUMULADO ---");

        System.out.println(
                "Promedio acumulado: " +
                calcularPromedioAcumulado(estudiante)
        );

        System.out.println(
                "Materias aprobadas: " +
                estudiante.contarMateriasAprobadasAcumuladas()
        );

        System.out.println(
                "Materias reprobadas: " +
                estudiante.contarMateriasReprobadasAcumuladas()
        );
    }

    // NAVEGACION ENTRE REPORTES CON PILAS

    private void abrirReporte() {

        System.out.println("\n--- ABRIR REPORTE ---");

        System.out.print("ID del estudiante: ");
        String id = teclado.nextLine();

        Estudiante estudiante = universidad.buscarEstudiante(id);

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        if (!reporteActual.equals("")) {
            pilaReportesAtras.push(reporteActual);
        }

        pilaReportesAdelante.clear();

        StringBuilder reporte = new StringBuilder();

        reporte.append("REPORTE ACADEMICO\n");
        reporte.append("Nombre: ").append(estudiante.getNombre()).append("\n");
        reporte.append("ID: ").append(estudiante.getId()).append("\n");
        reporte.append("Email: ").append(estudiante.getEmail()).append("\n");
        reporte.append("Semestre: ").append(estudiante.getSemestre()).append("\n");

        for (int semestre = 0; semestre < 10; semestre++) {

            reporte.append("\nSemestre ").append(semestre).append("\n");

            for (int posicion = 0; posicion < 20; posicion++) {

                Double nota = estudiante.consultarNota(semestre, posicion);

                if (nota != null) {

                    reporte.append("Materia ")
                            .append(posicion)
                            .append(": ")
                            .append(nota)
                            .append("\n");
                }
            }
        }

        reporteActual = reporte.toString();

        System.out.println(reporteActual);
    }

    private void navegarReportes() {

        System.out.println("\n--- NAVEGACION DE REPORTES ---");

        System.out.println("1. Reporte anterior");
        System.out.println("2. Reporte siguiente");

        System.out.print("Seleccione una opcion: ");

        int opcion = leerEntero();

        if (opcion == 1) {
            regresarReporte();
        } else if (opcion == 2) {
            avanzarReporte();
        } else {
            System.out.println("Opcion no valida.");
        }
    }

    private void regresarReporte() {

        if (pilaReportesAtras.isEmpty()) {
            System.out.println("No hay reportes anteriores.");
            return;
        }

        pilaReportesAdelante.push(reporteActual);
        reporteActual = pilaReportesAtras.pop();

        System.out.println(reporteActual);
    }

    private void avanzarReporte() {

        if (pilaReportesAdelante.isEmpty()) {
            System.out.println("No hay reportes siguientes.");
            return;
        }

        pilaReportesAtras.push(reporteActual);
        reporteActual = pilaReportesAdelante.pop();

        System.out.println(reporteActual);
    }

    // DESHACER OPERACIONES

    private void deshacerOperacion() {

        if (pilaDeshacer.isEmpty()) {
            System.out.println("No hay operaciones para deshacer.");
            return;
        }

        Operacion operacion = pilaDeshacer.pop();

        switch (operacion.tipo) {

            case "REGISTRAR_ESTUDIANTE":

                universidad.eliminarEstudiante(
                        operacion.estudiante.getId()
                );

                break;

            case "ELIMINAR_ESTUDIANTE":

                universidad.registrarEstudiante(
                        operacion.estudiante
                );

                break;

            case "REGISTRAR_MATERIA":

                universidad.eliminarMateria(
                        operacion.codigoMateria
                );

                break;

            case "INSCRIBIR":

                if (operacion.estabaEnEspera) {
                    operacion.materia.retirarDeListaEspera(
                            operacion.estudiante
                    );
                } else {
                    operacion.materia.cancelarInscripcion(
                            operacion.estudiante
                    );
                }

                break;

            case "CANCELAR_INSCRIPCION":

                operacion.materia.inscribirEstudiante(
                        operacion.estudiante
                );

                break;

            case "RESERVAR_AULA":

                operacion.aula.liberar(
                        operacion.dia,
                        operacion.hora,
                        operacion.duracion
                );

                break;

            case "LIBERAR_AULA":

                operacion.aula.reservar(
                        operacion.dia,
                        operacion.hora,
                        operacion.duracion
                );

                break;

            case "REGISTRAR_NOTA":

                operacion.estudiante.getNotas()
                        [operacion.semestre][operacion.posicion]
                        = operacion.notaAnterior;

                break;

            case "CAMBIAR_DISTANCIA":

                if (operacion.distanciaAnterior >= 0) {

                    universidad.registrarDistancia(
                            operacion.origen,
                            operacion.destino,
                            operacion.distanciaAnterior
                    );

                } else {

                    universidad.getDistancias()
                            [operacion.origen][operacion.destino] = -1;

                    universidad.getDistancias()
                            [operacion.destino][operacion.origen] = -1;
                }

                break;
        }

        pilaRehacer.push(operacion);

        System.out.println("Operacion deshecha correctamente.");
    }

    // REHACER OPERACIONES

    private void rehacerOperacion() {

        if (pilaRehacer.isEmpty()) {
            System.out.println("No hay operaciones para rehacer.");
            return;
        }

        Operacion operacion = pilaRehacer.pop();

        switch (operacion.tipo) {

            case "REGISTRAR_ESTUDIANTE":

                universidad.registrarEstudiante(
                        operacion.estudiante
                );

                break;

            case "ELIMINAR_ESTUDIANTE":

                universidad.eliminarEstudiante(
                        operacion.estudiante.getId()
                );

                break;

            case "REGISTRAR_MATERIA":

                universidad.registrarMateria(
                        operacion.materia
                );

                break;

            case "INSCRIBIR":

                if (operacion.estabaEnEspera) {
                    operacion.materia.inscribirEstudiante(
                            operacion.estudiante
                    );
                } else {
                    operacion.materia.inscribirEstudiante(
                            operacion.estudiante
                    );
                }

                break;

            case "CANCELAR_INSCRIPCION":

                operacion.materia.cancelarInscripcion(
                        operacion.estudiante
                );

                break;

            case "RESERVAR_AULA":

                operacion.aula.reservar(
                        operacion.dia,
                        operacion.hora,
                        operacion.duracion
                );

                break;

            case "LIBERAR_AULA":

                operacion.aula.liberar(
                        operacion.dia,
                        operacion.hora,
                        operacion.duracion
                );

                break;

            case "REGISTRAR_NOTA":

                operacion.estudiante.registrarNota(
                        operacion.semestre,
                        operacion.posicion,
                        operacion.notaNueva
                );

                break;

            case "CAMBIAR_DISTANCIA":

                universidad.registrarDistancia(
                        operacion.origen,
                        operacion.destino,
                        operacion.distanciaNueva
                );

                break;
        }

        pilaDeshacer.push(operacion);

        System.out.println("Operacion rehecha correctamente.");
    }

    // PROCESAMIENTO DE ARCHIVOS CSV

    private void cargarArchivoCSV() {

        System.out.println("\n--- CARGAR ARCHIVO CSV ---");

        System.out.print("Ruta del archivo CSV: ");
        String nombreArchivo = teclado.nextLine();

        colaSolicitudes.clear();

        try (BufferedReader lector =
                     Files.newBufferedReader(Path.of(nombreArchivo))) {

            String linea;

            // Se omite la primera linea porque contiene los encabezados.
            lector.readLine();

            while ((linea = lector.readLine()) != null) {

                if (!linea.trim().equals("")) {
                    colaSolicitudes.offer(linea);
                }
            }

            System.out.println(
                    "Solicitudes cargadas: " + colaSolicitudes.size()
            );

        } catch (IOException e) {

            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private void procesarSolicitudesBatch() {

        if (colaSolicitudes.isEmpty()) {
            System.out.println("No hay solicitudes para procesar.");
            return;
        }

        System.out.println("\n--- PROCESAMIENTO POR LOTES ---");

        int exitosas = 0;
        int enEspera = 0;
        int fallidas = 0;

        while (!colaSolicitudes.isEmpty()) {

            String solicitud = colaSolicitudes.poll();

            String[] datos = solicitud.split(",");

            if (datos.length != 2) {
                System.out.println("Solicitud invalida: " + solicitud);
                fallidas++;
                continue;
            }

            String id = datos[0].trim();
            String codigo = datos[1].trim();

            Estudiante estudiante =
                    universidad.buscarEstudiante(id);

            Materia materia =
                    universidad.buscarMateria(codigo);

            if (estudiante == null) {
                System.out.println(
                        "Solicitud fallida. Estudiante no encontrado: " + id
                );

                fallidas++;
                continue;
            }

            if (materia == null) {
                System.out.println(
                        "Solicitud fallida. Materia no encontrada: " + codigo
                );

                fallidas++;
                continue;
            }

            if (!materia.cumplePrerrequisitos(estudiante)) {

                System.out.println(
                        "Solicitud fallida. No cumple prerrequisitos: " + id
                );

                fallidas++;
                continue;
            }

            if (materia.inscribirEstudiante(estudiante)) {

                Operacion operacion = new Operacion("INSCRIBIR");
                operacion.estudiante = estudiante;
                operacion.materia = materia;
                operacion.estabaEnEspera = false;

                guardarOperacion(operacion);

                System.out.println(
                        "Inscripcion exitosa: " + id + " - " + codigo
                );

                exitosas++;

            } else if (materia.estaEnEspera(estudiante)) {

                Operacion operacion = new Operacion("INSCRIBIR");
                operacion.estudiante = estudiante;
                operacion.materia = materia;
                operacion.estabaEnEspera = true;

                guardarOperacion(operacion);

                System.out.println(
                        "Estudiante agregado a espera: " + id + " - " + codigo
                );

                enEspera++;

            } else {

                System.out.println(
                        "Solicitud fallida. No fue posible inscribir: " + id
                );

                fallidas++;
            }
        }

        System.out.println("\n--- RESUMEN DEL PROCESAMIENTO ---");

        System.out.println("Inscripciones exitosas: " + exitosas);
        System.out.println("Solicitudes en espera: " + enEspera);
        System.out.println("Solicitudes fallidas: " + fallidas);

        System.out.println("Procesamiento finalizado.");
    }

    // LECTURA DE DATOS

    private int leerEntero() {

        try {
            int numero = Integer.parseInt(teclado.nextLine());
            return numero;

        } catch (NumberFormatException e) {

            System.out.println("Debe ingresar un numero entero.");
            return -1;
        }
    }

    private double leerDecimal() {

        try {
            String valor = teclado.nextLine();
            valor = valor.replace(",", ".");

            return Double.parseDouble(valor);

        } catch (NumberFormatException e) {

            System.out.println("Debe ingresar un numero valido.");
            return -1;
        }
    }
}

