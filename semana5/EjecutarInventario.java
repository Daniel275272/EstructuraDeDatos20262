package semana5;

public class EjecutarInventario {
    static Producto[][] inventario = new Producto[3][4];

    public static void main(String[] args) {

        // Registro
        inventario[0][0] = new Producto(1, "Arroz",  2500, 20);
        inventario[0][1] = new Producto(2, "Leche",  3200, 15);
        inventario[0][2] = new Producto(3, "Pan",    1500, 30);
        inventario[1][0] = new Producto(4, "Café",   8000, 10);
        inventario[1][1] = new Producto(5, "Azúcar", 2800, 25);
        inventario[2][3] = new Producto(6, "Sal",    1200, 40);

        mostrar();
        buscar(4);
        valorTotal();
        mayorCantidadYVacias();
    }

    static void mostrar() {
        System.out.println("--- INVENTARIO ---");
        for (int f = 0; f < 3; f++) {
            for (int c = 0; c < 4; c++) {
                if (inventario[f][c] != null) {
                    System.out.println("[" + f + "][" + c + "] " + inventario[f][c]);
                }
            }
        }
    }

    static void buscar(int codigo) {
        System.out.println("\n BUSCAR CÓDIGO " + codigo );
        for (int f = 0; f < 3; f++) {
            for (int c = 0; c < 4; c++) {
                if (inventario[f][c] != null && inventario[f][c].getCodigo() == codigo) {
                    System.out.println("Encontrado en [" + f + "][" + c + "]: " + inventario[f][c]);
                    return;
                }
            }
        }
        System.out.println("No se encontró producto con ese código.");
    }

    static void valorTotal() {
        double total = 0;
        for (int f = 0; f < 3; f++) {
            for (int c = 0; c < 4; c++) {
                if (inventario[f][c] != null) {
                    total = total + inventario[f][c].getValorTotal();
                }
            }
        }
        System.out.println("\n VALOR TOTAL DEL INVENTARIO ");
        System.out.println("Total: $" + total);
    }

    static void mayorCantidadYVacias() {
        Producto mayor = null;
        int vacias = 0;

        for (int f = 0; f < 3; f++) {
            for (int c = 0; c < 4; c++) {
                if (inventario[f][c] == null) {
                    vacias++;
                } else {
                    if (mayor == null || inventario[f][c].getCantidad() > mayor.getCantidad()) {
                        mayor = inventario[f][c];
                    }
                }
            }
        }

        System.out.println("\n MAYOR CANTIDAD Y VACÍAS ");
        if (mayor != null) {
            System.out.println("Mayor cantidad: " + mayor);
        } else {
            System.out.println("No hay productos registrados.");
        }
        System.out.println("Posiciones vacías: " + vacias);
    }
}
