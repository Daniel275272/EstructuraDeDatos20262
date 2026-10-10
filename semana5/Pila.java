package semana5;

import java.util.Stack;

public class Pila {
    public static void main(String[] args) {
        Stack <Integer> pila = new Stack<> ();

        System.out.println("pila vacia: "+pila.empty());

        pila.push(5);
        pila.push(8);
        pila.push(10);
        pila.push(2);
        pila.push(20);
        pila.push(15);
        pila.push(1);

        System.out.println(pila);

        System.out.println("pila vacia: "+pila.empty());


        System.out.println("Tope de la pila: " + pila.peek() );

        System.out.println( "pos: " + pila.search(10));

        pila.pop();
        pila.pop();

        System.out.println(pila); 
        System.out.println("tamaño de la pila: "+pila.size());
    }
}
