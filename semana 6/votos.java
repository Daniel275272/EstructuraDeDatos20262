import java.util.LinkedList;
import java.util.Queue;

public class votos {

    public static void main(String[] args) {

        System.out.println("votacion 1: " + calcularDiferencia(13, 0));
        System.out.println("votacion 2: " + calcularDiferencia(2, 33));
        System.out.println("votacion 3: " + calcularDiferencia(132, 132));
    }

    public static int calcularDiferencia(int upvotes, int downvotes) {
        Queue<Integer> cola = new LinkedList<>();

        for (int i = 0; i < upvotes; i++) {
            cola.add(1);
        }
        for (int i = 0; i < downvotes; i++) {
            cola.add(-1);
        }
        int suma = 0;
        while (!cola.isEmpty()) {
            suma += cola.poll();
        }
        return suma;

    }
}
