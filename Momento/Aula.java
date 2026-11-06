package Momento;

public class Aula {
    private String codigo;
    private String nombre;
    private int capacidad;

    private boolean[][] disponibilidad;

    public Aula(String codigo, String nombre, int capacidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidad = capacidad;

        disponibilidad = new boolean[7][24];

        for (int dia = 0; dia < 7; dia++) {
            for (int hora = 0; hora < 24; hora++) {
                disponibilidad[dia][hora] = true;
            }
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean consultarDisponibilidad(int dia, int hora) {

        if (dia < 0 || dia >= 7 || hora < 0 || hora >= 24) {
            return false;
        }

        return disponibilidad[dia][hora];
    }

    public boolean reservar(int dia, int hora, int duracion) {

        if (!validarHorario(dia, hora, duracion)) {
            return false;
        }

        for (int i = hora; i < hora + duracion; i++) {
            if (!disponibilidad[dia][i]) {
                return false;
            }
        }

        for (int i = hora; i < hora + duracion; i++) {
            disponibilidad[dia][i] = false;
        }

        return true;
    }

    public boolean liberar(int dia, int hora, int duracion) {

        if (!validarHorario(dia, hora, duracion)) {
            return false;
        }

        for (int i = hora; i < hora + duracion; i++) {
            disponibilidad[dia][i] = true;
        }

        return true;
    }

    private boolean validarHorario(int dia, int hora, int duracion) {

        return dia >= 0 && dia < 7 && hora >= 0 && hora < 24 && duracion > 0 && hora + duracion <= 24;
    }

    public void mostrarInformacion() {
        System.out.println("Codigo: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Capacidad: " + capacidad);
    }
}
