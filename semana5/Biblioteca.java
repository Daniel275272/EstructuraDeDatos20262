package semana5;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
public class Biblioteca {
    static Libro[] libros = new Libro[10];
    static int cantidadLibros = 0;

    static Libro[][] secciones = new Libro[3][3];

    static Queue<SolicitudPrestamo> solicitudes = new LinkedList<>();
    static final int CAPACIDAD_COLA = 10;

    static Stack<OperacionBiblioteca> historial = new Stack<>();
    static final int CAPACIDAD_PILA = 10;

    static int contadorOperaciones = 1;

    
    static void registrarLibro(Libro libro) {
        if (cantidadLibros >= libros.length) {
            System.out.println("No hay espacio para más libros.");
            return;
        }
        libros[cantidadLibros] = libro;
        cantidadLibros++;
        registrarOperacion("Registrar libro: " + libro.getTitulo());
    }

    static void listarLibros() {
        System.out.println("\nLISTA DE LIBROS");
        if (cantidadLibros == 0) {
            System.out.println("No hay libros registrados.");
            return;
        }
        for (int i = 0; i < cantidadLibros; i++) {
            System.out.println((i + 1) + ". " + libros[i]);
        }
    }

    static void buscarLibro(int codigo) {
        for (int i = 0; i < cantidadLibros; i++) {
            if (libros[i].getCodigo() == codigo) {
                System.out.println("Encontrado: " + libros[i]);
                return;
            }
        }
        System.out.println("No se encontró libro con código " + codigo);
    }

   
    static void ubicarEnSeccion(int fila, int columna, Libro libro) {
        if (fila < 0 || fila >= 3 || columna < 0 || columna >= 3) {
            System.out.println("Posición inválida.");
            return;
        }
        if (secciones[fila][columna] != null) {
            System.out.println("Esa posición ya está ocupada.");
            return;
        }
        secciones[fila][columna] = libro;
        System.out.println("Ubicado en [" + fila + "][" + columna + "]: " + libro);
    }

    static void mostrarSecciones() {
        System.out.println("\nSECCIONES");
        for (int f = 0; f < 3; f++) {
            for (int c = 0; c < 3; c++) {
                if (secciones[f][c] != null) {
                    System.out.println("[" + f + "][" + c + "] " + secciones[f][c]);
                } else {
                    System.out.println("[" + f + "][" + c + "] (vacía)");
                }
            }
        }
    }

   
    static void registrarSolicitud(SolicitudPrestamo s) {
        if (solicitudes.size() >= CAPACIDAD_COLA) {
            System.out.println("Cola LLENA.");
            return;
        }
        solicitudes.offer(s);
        registrarOperacion("Solicitud registrada: " + s.getNumero());
        System.out.println("Solicitud registrada: " + s);
    }

    static void procesarSolicitud() {
        if (solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes pendientes.");
            return;
        }
        SolicitudPrestamo atendida = solicitudes.poll();
        registrarOperacion("Préstamo procesado: " + atendida.getNumero());
        System.out.println("Préstamo procesado: " + atendida);
    }

    static void mostrarSolicitudes() {
        System.out.println("\nSOLICITUDES PENDIENTES");
        if (solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes.");
            return;
        }
        for (SolicitudPrestamo s : solicitudes) {
            System.out.println(s);
        }
    }

    
    static void registrarOperacion(String descripcion) {
        if (historial.size() >= CAPACIDAD_PILA) {
            historial.pop();
        }
        historial.push(new OperacionBiblioteca(contadorOperaciones, descripcion));
        contadorOperaciones++;
    }

    static void mostrarHistorial() {
        System.out.println("\nHISTORIAL DE OPERACIONES");
        if (historial.empty()) {
            System.out.println("No hay operaciones registradas.");
            return;
        }
        for (int i = historial.size() - 1; i >= 0; i--) {
            System.out.println(historial.get(i));
        }
    }
}
