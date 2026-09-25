
public class EjecutarTaller {
    public static void main(String[] args) {
        
       OperacionesConArreglos objP10 = new OperacionesConArreglos();

        //Crear una arreglo
        int[] a = objP10.llenarArreglo(5, 1, 20);
        
        //Mostrar el arreglo
        objP10.mostrarArreglo(a);

        //llamar el método sumarExtremos
        int[] b = objP10.sumaExtremos(a);

        //Mostrar el arreglo
        objP10.mostrarArreglo(b);
    }
}
