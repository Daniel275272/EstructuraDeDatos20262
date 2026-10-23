package semana5;

public class Operacion {
    private int identificador;
    private String descripcion;
    private String tipo;

    public Operacion(int identificador, String descripcion, String tipo) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Operacion{id=" + identificador + ", descripcion=" + descripcion + ", tipo=" + tipo + "}";
    }
}
