package semana5;

public class SolicitudPrestamo {
    private int numero;
    private String estudiante;
    private int codigoLibro;

    public SolicitudPrestamo(int numero, String estudiante, int codigoLibro) {
        this.numero = numero;
        this.estudiante = estudiante;
        this.codigoLibro = codigoLibro;
    }

    public int getNumero() { return numero; }
    public String getEstudiante() { return estudiante; }
    public int getCodigoLibro() { return codigoLibro; }

    @Override
    public String toString() {
        return "Solicitud{numero=" + numero +
               ", estudiante=" + estudiante +
               ", codigoLibro=" + codigoLibro + '}';
    }
}
