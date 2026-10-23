package semana5;

import java.util.Stack;

public class EjecutarOperaciones {
     static Stack<Operacion> pila = new Stack<>();
    static final int CAPACIDAD = 10;

    public static void main(String[] args) {

        apilar(new Operacion(1, "Registrar libro", "Préstamo"));
        apilar(new Operacion(2, "Devolver libro", "Devolución"));
        apilar(new Operacion(3, "Actualizar catálogo", "Mantenimiento"));
        apilar(new Operacion(4, "Registrar usuario", "Préstamo"));
        apilar(new Operacion(5, "Consultar historial", "Consulta"));

        mostrar();
        tope();

        System.out.println("\nDESAPILAR DOS VECES (LIFO)");
        desapilar();
        desapilar();

        mostrar();
        tope();

        System.out.println("\nVACIAR TODA LA PILA");
        while (!pila.empty()) {
            desapilar();
        }

        tope();
        desapilar();
    }

    static void apilar(Operacion op) {
        if (pila.size() >= CAPACIDAD) {
            System.out.println("Pila LLENA: " + op);
            return;
        }
        pila.push(op);
        System.out.println("Apilada: " + op);
    }

    static void desapilar() {
        if (pila.empty()) {
            System.out.println("Pila VACÍA.");
            return;
        }
        System.out.println("Desapilada: " + pila.pop());
    }

    static void tope() {
        if (pila.empty()) {
            System.out.println("Pila VACÍA.");
            return;
        }
        System.out.println("Tope: " + pila.peek());
    }

    static void mostrar() {
        System.out.println("\nCONTENIDO DE LA PILA");
        if (pila.empty()) {
            System.out.println("Pila vacía.");
            return;
        }
        for (int i = pila.size() - 1; i >= 0; i--) {
            System.out.println((i + 1) + ". " + pila.get(i));
        }
    }
}
