import java.util.LinkedList;
import java.util.Queue;


public class CuatroLetras {
    public static String[] filtrar(String[] palabras) {
        
        if (palabras == null) {
            return new String[0];
        }
       
        if (palabras.length == 0) {
            return new String[0];
        }

        
        Queue<String> cola = new LinkedList<>();
        for (String palabra : palabras) {
            cola.offer(palabra);
        }

        
        String[] temporales = new String[palabras.length];
        int contador = 0;

       
        while (!cola.isEmpty()) {
            String palabra = cola.poll();
            if (palabra.length() == 4) {
                temporales[contador] = palabra;
                contador++;
            }
        }

       
        String[] palabrasFiltradas = new String[contador];
        for (int i = 0; i < contador; i++) {
            palabrasFiltradas[i] = temporales[i];
        }

        return palabrasFiltradas;
    }

    public static void main(String[] args) {
        String[] pt1 = {"Tomate", "Papa", "Par"};
        imprimir(filtrar(pt1));  

        String[] pt2 = {"Luz", "Casa", "Sol", "Lago"};
        imprimir(filtrar(pt2));  

        String[] pt3 = {"Azul", "Rojo", "Verde"};
        imprimir(filtrar(pt3));  
    }

    
    public static void imprimir(String[] j) {
        String salida = "[";
        for (int i = 0; i < j.length; i++) {
            salida = salida + j[i];
            if (i < j.length - 1) {
                salida = salida + ", ";
            }
        }
        salida = salida + "]";
        System.out.println(salida);
    }
}
