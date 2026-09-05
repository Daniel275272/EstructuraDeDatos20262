package Semana1;

public class Ejecutar {
    public static void main(String[] args) {
        int[] a = {5, 16, 8, 9, 10, 25, 19, 3, 6, 22, 2};
        
            for (int i = 0; i < a.length; i++) {
        System.out.println("a[" + i + "]=" + a[i]);
        }
        int suma=0;
        for (int i = 0; i < a.length; i++) {
            suma+=a[i];
        }
        System.out.println("la suma de los elementos del arreglo a"+ suma);

        int[] b = new int[11];
        int min=1, max=50;
        for (int i = 0; i < b.length; i++) {
            b[i]=(int) (Math.random()* (max-min-1)+min);
        }

        for (int i = 0; i < b.length; i++){
            System.out.println("b["+i+"]="+b[i]);
        }

        for (int i = 0; i < b.length; i++) {
            System.out.println("b[" + i + "]=" + b[i] + " potencia 2 = " + Math.pow(b[i],2));
        }

    }
}
