public class Ejecutar {
    public static void main(String[] args) {
        OperacionesMatrices objM = new OperacionesMatrices();

        int [][] matriz=objM.llenarMatriz(3, 3, 1, 9);

        System.out.println(objM.imprimirMatriz(matriz));

        System.out.println(objM.sumaFilasColumnas(matriz));
        //18
        int[][] tabla = objM.tablaMultiplicar(10);
        System.out.println(objM.imprimirMatriz(tabla));
        //19
        int[][] identidad = objM.matrizIdentidad(5);
        System.out.println(objM.imprimirMatriz(identidad));
        //20
        int[][] m20 = objM.matrizCerosArriba(10);
        System.out.println(objM.imprimirMatriz(m20));

    }
    
}
