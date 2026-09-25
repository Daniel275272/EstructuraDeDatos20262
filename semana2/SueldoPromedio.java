public class SueldoPromedio {
    public static void main(String[] args) {

         String[] nombres = {"Ana", "Luis", "María", "Carlos", "Laura"};
        double[] sueldos = {20000, 30000, 25000, 25000, 25000};

        double suma = 0;
        for (int i = 0; i < sueldos.length; i++) {
            suma += sueldos[i];
        }

        double promedio = suma / sueldos.length;
        System.out.println("Sueldo promedio: " + promedio);

        int contador = 0;
        String quienes = "";
        for (int i = 0; i < sueldos.length; i++) {
            if (sueldos[i] == promedio) {
                contador++;
                quienes += nombres[i] + " ";
            }
        }

        System.out.println("Cantidad con sueldo promedio: " + contador);
        System.out.println("Quiénes: " + quienes);
    }
}
 
