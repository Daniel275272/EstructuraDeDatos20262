package semana5;
import java.util.LinkedList;
import java.util.Queue;


public class EjecutarSolicitudes {
    static Queue<Solicitud> cola = new LinkedList<>();
    static final int CAPACIDAD = 10;

    public static void main(String[] args) {

        registrar(new Solicitud(1, "Ana", "Matrícula"));
        registrar(new Solicitud(2, "Luis", "Certificado"));
        registrar(new Solicitud(3, "Sofía", "Homologación"));
        registrar(new Solicitud(4, "Carlos", "Matrícula"));
        registrar(new Solicitud(5, "Marta", "Certificado"));

        mostrar();
        siguiente();

        System.out.println("\nATENDER DOS SOLICITUDES (FIFO)");
        atender();
        atender();

        mostrar();
        siguiente();

        System.out.println("\nATENDER TODAS LAS SOLICITUDES");
        while (!cola.isEmpty()) {
            atender();
        }

        siguiente();
        atender();
    }

    static void registrar(Solicitud s) {
        if (cola.size() >= CAPACIDAD) {
            System.out.println("Cola LLENA: " + s);
            return;
        }
        cola.offer(s);
        System.out.println("Registrada: " + s);
    }

    static void atender() {
        if (cola.isEmpty()) {
            System.out.println("Cola VACÍA.");
            return;
        }
        System.out.println("Atendida: " + cola.poll());
    }

    static void siguiente() {
        if (cola.isEmpty()) {
            System.out.println("Cola VACÍA.");
            return;
        }
        System.out.println("Siguiente: " + cola.peek());
    }

    static void mostrar() {
        System.out.println("\nSOLICITUDES PENDIENTES");
        if (cola.isEmpty()) {
            System.out.println("Cola vacía.");
            return;
        }
        for (Solicitud s : cola) {
            System.out.println(s);
        }
    }
}
