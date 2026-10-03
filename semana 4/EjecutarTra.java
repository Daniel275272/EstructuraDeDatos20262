public class EjecutarTra{

    public static void main(String[] args) {
        
        //Creación de un objeto trabajador
        Trabajador objT1 = new Trabajador(1,"Juan","Peréz", 20, 1000.0);
        Trabajador objT2 = new Trabajador(2,"Lina","Cuero", 21, 1500.0);
        Trabajador objT3 = new Trabajador(3,"Git","Hub", 30, 800.0);
        
        System.out.println(objT1);
        System.out.println(objT2);
        System.out.println(objT1.getNombre());
        //arreglo de obj
        Trabajador[] t = new Trabajador[3];
        t[0] = objT1;
        t[1] = objT2;
        t[2] = objT3;

        double totalSalarios = objT1.calcularSalario(t);
        System.out.println("Suma de los salarios es: " + totalSalarios);
        System.out.println("promedio de las edades: "+ objT1.promedioEdades(t));

        // creacion de objetos operario y vendedor
        Trabajador objOperario1 = new Operario(101,"x","y",25,15000.0,40);
        Trabajador objVendedor1 = new Vendedor(256,"x","y",24,10000.0,20);
        Trabajador objVendedor2 = new Vendedor(200,"x","y",30,20000.0,30);

        //System.out.println("pago total: " + objOperario1.pagar());
        //System.out.println("pago total: " + objVendedor1.pagar());
        

        Trabajador[] s = new Trabajador[3];
        s[0] = objOperario1;
        s[1] = objVendedor1;
        s[2] = objVendedor2;
         for(int i = 0; i < s.length; i++){
            System.out.println("Salario mes: " + s[i].pagar());
        }

    }
}
