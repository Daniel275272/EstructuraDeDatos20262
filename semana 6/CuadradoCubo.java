
import java.util.Stack;
public class CuadradoCubo {
    public static boolean raizIgual(int a, int b){
         Stack <Integer> pila = new Stack<> ();
         pila.push(a);
         pila.push(b);

         int valorB = pila.pop();
         int valorA = pila.pop();

         double raizA = Math.sqrt(valorA);
         double raizB = Math.cbrt(valorB);
         return raizA == raizB;
        

    }
    public static void main(String[] args){
        System.out.println( "las raices dan mismo resultado: "+raizIgual(4, 8));
        System.out.println( "las raices dan mismo resultado: "+raizIgual(16, 48));
        System.out.println( "las raices dan mismo resultado: "+raizIgual(9, 27));
    }
}
