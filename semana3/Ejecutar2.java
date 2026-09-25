public class Ejecutar2 {
    public static void main(String[] args) {
        OperacionesMatrices2 objM = new OperacionesMatrices2();

        //21
        int[][] m21 = objM.llenarMatriz(4, 4, 1, 20);
        System.out.println(objM.imprimirMatriz(m21));
        int[] vectorPares = objM.sumaParesFilas(m21);
        System.out.print("Suma pares por fila: ");
        for (int i = 0; i < vectorPares.length; i++) {
            System.out.print(vectorPares[i] + " ");
        }
        System.out.println("\n");

        // 22
        int[][] m22 = objM.llenarMatriz(3, 4, 1, 50);
        System.out.println(objM.imprimirMatriz(m22));
        System.out.println(objM.mayorMenorMatriz(m22));
        System.out.println();

        // 23
        int[][] m23 = objM.llenarMatriz(4, 4, -20, 20);
        System.out.println("Original:");
        System.out.println(objM.imprimirMatriz(m23));
        System.out.println("Con mayor absoluto en ultima columna:");
        System.out.println(objM.imprimirMatriz(objM.mayorAbsolutoFilas(m23)));
    }
    
}
