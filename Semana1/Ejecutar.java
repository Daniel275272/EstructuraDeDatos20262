package Semana1;

public class Ejecutar {
    public static void main(String[] args) {
        
        //Declaración del arreglo-array-vector (Forma 1)
        int[] a = {5, 16, 8, 9, 10, 25, 19, 3, 6, 22, 2};

        for (int i = 0; i < a.length; i++) {
            System.out.println("a[" + i + "]=" + a[i]);
        }

        
        int suma = 0;
        for (int i = 0; i < a.length; i++) {
            suma += a[i];
        }

        System.out.println("La suma de los elementos del arreglo a: " + suma);

        
        int[] b = new int[11];
        int min = 1, max = 30;
        for (int i = 0; i < b.length; i++) {
            //Llenar el arreglo b con números aleatorios entre 1(min) y 50(max)
            b[i] = (int)(Math.random() * (max - min - 1 ) + min);
        } 

        
        for (int i = 0; i < b.length; i++) {
            System.out.println("b[" + i + "]=" + b[i] + " potencia 2 = " + Math.pow(b[i],2));
        }

        int sumaPar = 0 , sumaImpar = 0;
        for (int i = 0; i < a.length; i++) {
            if(a[i] % 2 == 0){
                sumaPar += a[i];
            }else{
                sumaImpar += a[i];
            }
        }

        System.out.println("Suma pares: " + sumaPar + " - Suma impares: " + sumaImpar);

        //7

        int cantPares = 0, cantImpares = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 0) {
                cantPares++;
            } else {
                cantImpares++;
            }
        }
        
        
        int[] pares = new int[cantPares];
        int[] impares = new int[cantImpares];
        
       
        int indicePar = 0, indiceImpar = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 0) {
                pares[indicePar] = a[i];
                indicePar++;
            } else {
                impares[indiceImpar] = a[i];
                indiceImpar++;
            }
        }
        
       
        System.out.print("Arreglo de PARES: ");
        for (int i = 0; i < pares.length; i++) {
            System.out.print(pares[i] + " ");
        }
        System.out.println();
        
        System.out.print("Arreglo de IMPARES: ");
        for (int i = 0; i < impares.length; i++) {
            System.out.print(impares[i] + " ");
        }
        System.out.println();



        //8
        
        int[] numeros = new int[30];
        int minAleatorio = 1, maxAleatorio = 50; // rango de los aleatorios
        
        
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = (int)(Math.random() * (maxAleatorio - minAleatorio + 1) + minAleatorio);
        }
        
        
        System.out.print("Arreglo generado: ");
        for (int i = 0; i < numeros.length; i++) {
            System.out.print(numeros[i] + " ");
        }
        System.out.println();
        
        
        int mayor = numeros[0];
        int menor = numeros[0];
        for (int i = 1; i < numeros.length; i++) {
            if (numeros[i] > mayor) {
                mayor = numeros[i];
            }
            if (numeros[i] < menor) {
                menor = numeros[i];
            }
        }
        
        
        int repeticionesMayor = 0;
        int repeticionesMenor = 0;
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == mayor) {
                repeticionesMayor++;
            }
            if (numeros[i] == menor) {
                repeticionesMenor++;
            }
        }
        
        
        System.out.println("Número MAYOR: " + mayor + " -> se repite " + repeticionesMayor + " veces");
        System.out.println("Número MENOR: " + menor + " -> se repite " + repeticionesMenor + " veces");

        //9

        
        int x = 8; 
        int contadorX = 0;
        
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) {
                contadorX++;
            }
        }
        
        System.out.println("El número " + x + " se encuentra " + contadorX + " veces en el arreglo a.");

    

    }
}