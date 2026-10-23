package semana5;

public class OperacionBiblioteca {
    private int id;
    private String descripcion;

    public OperacionBiblioteca(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() { return id; }
    public String getDescripcion() { return descripcion; }

    @Override
    public String toString() {
        return "Operacion{id=" + id + ", descripcion=" + descripcion + '}';
    }
}
