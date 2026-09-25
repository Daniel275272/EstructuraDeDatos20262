public class Ejecutar3 {
     public static void main(String[] args) {
        OperacionesMatrices3 objM = new OperacionesMatrices3();

        // 24
        
        double[] produccion = objM.produccionMensual();
        System.out.println(objM.analizarProduccion(produccion));
        System.out.println();

        // 25
        
        int[][] plantas = objM.llenarMatriz(20, 7, 1, 100);
        System.out.println(objM.plantaMayorSemanal(plantas));
        System.out.println(objM.plantaMayorDia(plantas, 3));
        System.out.println();

        // 26
        
        int[][] ventas = objM.llenarMatriz(8, 30, 1, 20);
        double[] precios = objM.preciosModelos(8);

        System.out.println(" Total por modelo ");
        System.out.println(objM.totalPorModelo(ventas, precios));

        System.out.println("Total por dia");
        System.out.println(objM.totalPorDia(ventas, precios));
    }
}
