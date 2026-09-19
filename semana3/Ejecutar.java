package semana3;

public class Ejecutar {
    public static void main(String[] args) {
        OperacionesMatrices objM = new OperacionesMatrices();

        int [][] matriz=objM.llenarMatriz(3, 3, 1, 9);

        System.out.println(objM.imprimirMatriz(matriz));

        System.out.println(objM.sumaFilasColumnas(matriz));

    }
}
