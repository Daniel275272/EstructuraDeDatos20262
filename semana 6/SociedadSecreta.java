import java.util.LinkedList;
import java.util.Queue;

public class SociedadSecreta {
    public static String nombreSociedad(String[] nombres) {
        if (nombres == null) {
            return "";
        }
        if (nombres.length == 0) {
            return "";
        }

        Queue<String> cola = new LinkedList<>();
        for (String nombre : nombres) {
            cola.offer(nombre);
        }

        String[] letras = new String[nombres.length];
        int i = 0;

        while (!cola.isEmpty()) {
            String nombre = cola.poll();
            letras[i] = nombre.substring(0, 1).toUpperCase();
            i++;
        }

        
        for (int a = 0; a < letras.length - 1; a++) {
            for (int b = 0; b < letras.length - 1 - a; b++) {
                if (letras[b].compareTo(letras[b + 1]) > 0) {
                    String temp = letras[b];
                    letras[b] = letras[b + 1];
                    letras[b + 1] = temp;
                }
            }
        }

        String resultado = "";
        for (int j = 0; j < letras.length; j++) {
            resultado = resultado + letras[j];
        }

        return resultado;
    }

    public static void main(String[] args) {
        String[] ej1 = {"Adam", "Sarah", "Malcolm"};
        System.out.println(nombreSociedad(ej1)); 

        String[] ej2 = {"bruce", "clark", "diana"};
        System.out.println(nombreSociedad(ej2)); 

        String[] ej3 = {"Zoe", "anna", "leo"};
        System.out.println(nombreSociedad(ej3)); 
    }
}
