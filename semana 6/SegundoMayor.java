import java.util.LinkedList;
import java.util.Queue;


public class SegundoMayor {
    public static int segundoMayor(int[] numeros) {
        
        if (numeros == null) {
            return -1;
        }
        
        if (numeros.length < 2) {
            return -1;
        }

        
        Queue<Integer> cola = new LinkedList<>();
        for (int n : numeros) {
            cola.offer(n);
        }

        
        int max1 = cola.poll();
        int max2 = cola.poll();

        
        if (max2 > max1) {
            int temp = max1;
            max1 = max2;
            max2 = temp;
        }

        
        while (!cola.isEmpty()) {
            int x = cola.poll();

            if (x > max1) {
                max2 = max1;
                max1 = x;
            } else if (x > max2 && x < max1) {
                max2 = x;
            }
        }

        return max2;
    }

    public static void main(String[] args) {
        int[] grupo1 = {10, 40, 30, 20, 50};
        System.out.println(segundoMayor(grupo1));  

        int[] grupo2 = {5, 5, 5, 5, 9};
        System.out.println(segundoMayor(grupo2)); 

        int[] grupo3 = {-3, -1, -7, -2};
        System.out.println(segundoMayor(grupo3));  
    }
}
