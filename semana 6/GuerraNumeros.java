import java.util.LinkedList;
import java.util.Queue;

public class GuerraNumeros {
    public static int diferenciaParesImpares(int[] numeros) {
        
        if (numeros == null) {
            return 0;
        }
        
        if (numeros.length == 0) {
            return 0;
        }

        
        Queue<Integer> cola = new LinkedList<>();
        for (int n : numeros) {
            cola.offer(n);
        }

        int sumaPares = 0;
        int sumaImpares = 0;

        
        while (!cola.isEmpty()) {
            int x = cola.poll();

            if (x % 2 == 0) {
                sumaPares = sumaPares + x;      
            } else {
                sumaImpares = sumaImpares + x;  
            }
        }

        
        int diferencia = sumaImpares - sumaPares;
        if (diferencia < 0) {
            diferencia = -diferencia;
        }

        return diferencia;
    }

    public static void main(String[] args) {
        int[] pr1 = {2, 8, 7, 5};
        System.out.println("La diferencia es: "+diferenciaParesImpares(pr1));  

        int[] pr2 = {1, 3, 5, 9};
        System.out.println("La diferencia es: "+diferenciaParesImpares(pr2));  

        int[] pr3 = {2, 4, 6, 8};
        System.out.println("La diferencia es: "+diferenciaParesImpares(pr3));  
    }


}
