package semana5;

public class Solicitud {
    private int numero;
    private String nombreEstudiante;
    private String tipoTramite;

    public Solicitud(int numero, String nombreEstudiante, String tipoTramite) {
        this.numero = numero;
        this.nombreEstudiante = nombreEstudiante;
        this.tipoTramite = tipoTramite;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    @Override
    public String toString() {
        return "Solicitud{numero=" + numero +
               ", estudiante=" + nombreEstudiante +
               ", tramite=" + tipoTramite + '}';
    }
}
