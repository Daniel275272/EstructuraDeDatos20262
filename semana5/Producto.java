package semana5;

public class Producto {
     private int codigo;
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto(int codigo, String nombre, double precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }

    public double getValorTotal() {
        return precio * cantidad;
    }

    @Override
    public String toString() {
        return "Producto{codigo=" + codigo +
               ", nombre=" + nombre +
               ", precio=" + precio +
               ", cantidad=" + cantidad + '}';
    }
}
