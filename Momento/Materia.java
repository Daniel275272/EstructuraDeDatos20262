
package Momento;

import java.util.LinkedList;
import java.util.Queue;

public class Materia {

    private String codigo;
    private String nombre;
    private int cuposMaximos;
    private int creditos;

    private LinkedList<Estudiante> estudiantesInscritos;
    private LinkedList<Materia> prerrequisitos;
    private Queue<Estudiante> colaEspera;

    public Materia(String codigo, String nombre, int cuposMaximos, int creditos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cuposMaximos = cuposMaximos;
        this.creditos = creditos;

        estudiantesInscritos = new LinkedList<>();
        prerrequisitos = new LinkedList<>();
        colaEspera = new LinkedList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public int getCuposMaximos() {
        return cuposMaximos;
    }

    public boolean agregarPrerrequisito(Materia materia) {
        if (materia == null || materia == this ||
            prerrequisitos.contains(materia)) {
            return false;
        }

        prerrequisitos.add(materia);
        return true;
    }

    public boolean cumplePrerrequisitos(Estudiante estudiante) {
        if (estudiante == null) {
            return false;
        }

        for (Materia materia : prerrequisitos) {
            if (!estudiante.tieneAprobada(materia)) {
                return false;
            }
        }

        return true;
    }

    public void mostrarPrerrequisitos() {
        if (prerrequisitos.isEmpty()) {
            System.out.println("Esta materia no tiene prerrequisitos.");
            return;
        }

        for (Materia materia : prerrequisitos) {
            System.out.println(materia.getCodigo() + " - " + materia.getNombre());
        }
    }

    public boolean inscribirEstudiante(Estudiante estudiante) {
        if (estudiante == null ||
            estudiantesInscritos.contains(estudiante) ||
            colaEspera.contains(estudiante) ||
            !cumplePrerrequisitos(estudiante)) {
            return false;
        }

        if (estudiantesInscritos.size() < cuposMaximos) {
            estudiantesInscritos.add(estudiante);
            return true;
        }

        colaEspera.offer(estudiante);
        return false;
    }

    public boolean cancelarInscripcion(Estudiante estudiante) {
        if (!estudiantesInscritos.remove(estudiante)) {
            return false;
        }

        asignarCupoDisponible();
        return true;
    }

    public boolean retirarDeListaEspera(Estudiante estudiante) {
        return colaEspera.remove(estudiante);
    }

    public boolean estaInscrito(Estudiante estudiante) {
        return estudiantesInscritos.contains(estudiante);
    }

    public boolean estaEnEspera(Estudiante estudiante) {
        return colaEspera.contains(estudiante);
    }

    public void mostrarColaEspera() {
        if (colaEspera.isEmpty()) {
            System.out.println("No hay estudiantes en espera.");
            return;
        }

        for (Estudiante estudiante : colaEspera) {
            System.out.println(estudiante.getNombre());
        }
    }

    public void asignarCupoDisponible() {
        if (estudiantesInscritos.size() < cuposMaximos &&
            !colaEspera.isEmpty()) {
            Estudiante estudiante = colaEspera.poll();
            estudiantesInscritos.add(estudiante);
        }
    }

    public int cantidadInscritos() {
        return estudiantesInscritos.size();
    }

    public int cantidadEnEspera() {
        return colaEspera.size();
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }

        if (!(objeto instanceof Materia)) {
            return false;
        }

        Materia otra = (Materia) objeto;
        return codigo.equals(otra.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}
