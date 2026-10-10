import java.util.LinkedList;
import java.util.Queue;

public class Cola {
    public static void main(String[] args) {
        
        Queue<String> cola = new LinkedList<>();
        cola.add("pedro");
        cola.add("juan");
        cola.add("maria");
        cola.offer("miguel");
        cola.offer("daniel");
        System.out.println(cola);

        System.out.println(cola.peek());
        System.out.println(cola.element());


        System.out.println("-----------------------");
        System.out.println(cola.remove()); 
        System.out.println(cola.poll()); 
        System.out.println(cola);

        System.out.println(cola.isEmpty());
        System.out.println(cola.contains("miguel"));
        System.out.println(cola.contains("pedro"));

        String cad = "Hola";

        System.out.println(cad.toUpperCase());
        System.out.println(cad.toLowerCase());
    }
}
