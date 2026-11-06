import java.util.LinkedList;
import java.util.Queue;


public class NumeroTelefonico {
     public static String formatear(int[] digitos) {
     
        if (digitos == null) {
            return "Error: debe entregar exactamente 10 dígitos";
        }
       
        if (digitos.length != 10) {
            return "Error: debe entregar exactamente 10 dígitos";
        }

        
        Queue<Integer> cola = new LinkedList<>();
        for (int digito : digitos) {
            cola.offer(digito);
        }

        
        String resultado = "(" + cola.poll() + cola.poll() + cola.poll() + ") " + cola.poll() + cola.poll() + cola.poll() + "-" + cola.poll() + cola.poll() + cola.poll() + cola.poll();

        return resultado;
    }

    public static void main(String[] args) {
        int[] numero1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        System.out.println(formatear(numero1)); 

        int[] numero2 = {3, 0, 0, 1, 2, 3, 4, 5, 6, 7};
        System.out.println(formatear(numero2)); 

        
        int[] numero3 = {1, 2, 3};
        System.out.println(formatear(numero3));  
    }

}
